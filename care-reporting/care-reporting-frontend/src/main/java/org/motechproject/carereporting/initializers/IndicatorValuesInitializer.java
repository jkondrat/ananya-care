package org.motechproject.carereporting.initializers;

import org.apache.log4j.Logger;
import org.motechproject.carereporting.context.ApplicationContextProvider;
import org.motechproject.carereporting.domain.FrequencyEntity;
import org.motechproject.carereporting.domain.IndicatorEntity;
import org.motechproject.carereporting.exception.CareRuntimeException;
import org.motechproject.carereporting.indicator.IndicatorCalculator;
import org.motechproject.carereporting.service.CronService;
import org.motechproject.carereporting.service.IndicatorService;
import org.motechproject.carereporting.utils.date.DateResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

public class IndicatorValuesInitializer implements Runnable {

    private static final Logger LOG = Logger.getLogger(IndicatorValuesInitializer.class);

    private ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

    private IndicatorEntity indicatorEntity;

    private CronService cronService;

    private IndicatorService indicatorService;

    private IndicatorCalculator indicatorCalculator;

    public IndicatorValuesInitializer(IndicatorEntity indicatorEntity) {
        this.indicatorEntity = indicatorEntity;
        indicatorService = applicationContext.getBean(IndicatorService.class);
        cronService = applicationContext.getBean(CronService.class);
        indicatorCalculator = applicationContext.getBean(IndicatorCalculator.class);
    }

    @Override
    @Transactional(readOnly = false)
    public void run() {
        LOG.info(indicatorEntity.getName() + ": start calculation");

        try {
            Date startDate = cronService.getDateDepth();
            Set<FrequencyEntity> frequencyEntities = cronService.getAllFrequencies();

            for (FrequencyEntity frequencyEntity: frequencyEntities) {
                Date endDate = DateResolver.resolveDates(frequencyEntity, new Date())[1];
                Date date = new Date(startDate.getTime());

                while (date.before(endDate)) {
                    Date[] dates = DateResolver.resolveDates(frequencyEntity, date, date);
                    indicatorCalculator.calculateIndicatorValues(indicatorEntity, frequencyEntity, dates[0], dates[1]);
                    date = dates[1];
                }
            }
        } catch(RuntimeException e) {
            indicatorService.setComputedForIndicator(indicatorEntity, true);
            LOG.info(indicatorEntity.getName() + ": calculation failed");
            throw new CareRuntimeException(e);
        }
        indicatorService.setComputedForIndicator(indicatorEntity, true);
        LOG.info(indicatorEntity.getName() + ": calculation finished");
    }
}
