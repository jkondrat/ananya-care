package org.motechproject.care.schedule.service.integration;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.delivery.schedule.util.FakeSchedule;
import org.motechproject.delivery.schedule.util.ScheduleVisualization;
import org.motechproject.delivery.schedule.util.ScheduleWithCapture;
import org.motechproject.delivery.schedule.util.SetDateAction;
import org.motechproject.scheduletracking.api.service.ScheduleTrackingService;
import org.motechproject.testing.utils.BaseUnitTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.Date;

import static org.motechproject.scheduletracking.api.domain.WindowName.*;
import static org.motechproject.util.DateUtil.newDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext-Scheduling.xml")
public class CareSchedulesIntegrationTest extends BaseUnitTest {
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;

    @Autowired
    private ScheduleTrackingService trackingService;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private ScheduleWithCapture schedule;
    private ScheduleVisualization visualization;

    @BeforeClass
    public static void turnOffSpringLogging() {
        Logger logger = Logger.getLogger("org.springframework");
        logger.setLevel(Level.FATAL);
    }

    @Before
    public void setUp() throws Exception {
        FakeSchedule fakeSchedule = new FakeSchedule(trackingService, schedulerFactoryBean, new SetDateAction() {
            @Override
            public void setTheDateTo(LocalDate date) {
                mockCurrentDate(date);
            }
        });

        String outputDir = null;
        if (new File("ananya-care-scheduling").exists()) {
            outputDir = "ananya-care-scheduling/doc/schedules/";
        }
        else if (new File("doc").exists()) {
            outputDir = "doc/schedules/";
        }
        visualization = new ScheduleVisualization(fakeSchedule, outputDir);

        schedule = new ScheduleWithCapture(fakeSchedule, visualization);
    }

    @Test
    public void shouldProvideAlertsForTetanusToxoidVaccinationAtTheRightTimes() throws Exception {
        schedule.withFulfillmentDates(date(25, JANUARY)).enrollFor("TT Vaccination", newDate(2012, 1, 1), null);

        schedule.assertNoAlerts("TT 1", earliest);
        schedule.assertAlertsStartWith("TT 1", due, date(1, JANUARY));
        schedule.assertNoAlerts("TT 1", late);
        schedule.assertNoAlerts("TT 1", max);

        schedule.assertNoAlerts("TT 2", earliest);
        schedule.assertAlerts("TT 2", due, date(8, FEBRUARY));
        schedule.assertNoAlerts("TT 2", late);
        schedule.assertNoAlerts("TT 2", max);

        visualization.outputTo("mother-tetanus-.html", 2);
    }

    @Test
    public void shouldProvideAlertsForMeaslesVaccinationAtTheRightTimes() throws Exception {
        schedule.enrollFor("Measles Vaccination", newDate(2011, 12, 1), null);

        schedule.assertNoAlerts("Measles", earliest);
        schedule.assertAlertsStartWith("Measles", due, date(1, SEPTEMBER));
        schedule.assertNoAlerts("Measles", late);
        schedule.assertNoAlerts("Measles", max);
        visualization.outputTo("child-measles-.html", 2);
    }

    private Date date(int day, int month) {
        return dateWithYear(day, month, 2012);
    }

    private Date dateWithYear(int day, int month, int year) {
        return new DateTime(year, month, day, 00, 0).toDate();
    }
}
