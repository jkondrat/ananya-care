package org.dwQueryBuilder.builders;

import org.dwQueryBuilder.data.DwQueryCombination;
import org.dwQueryBuilder.data.GroupBy;
import org.dwQueryBuilder.data.SelectColumn;
import org.dwQueryBuilder.data.conditions.where.WhereConditionGroup;
import org.dwQueryBuilder.data.queries.SimpleDwQuery;

import java.util.LinkedHashSet;
import java.util.Set;

public class SimpleDwQueryBuilder {
    private Set<SelectColumn> selectColumns;
    private GroupBy groupBy;
    private WhereConditionGroup whereConditionGroup;
    private Set<DwQueryCombination> combineWith;
    private String tableName;

    public SimpleDwQueryBuilder withSelectColumn(SelectColumn selectColumn) {
        if (this.selectColumns == null) {
            this.selectColumns = new LinkedHashSet<>();
        }

        this.selectColumns.add(selectColumn);
        return this;
    }

    public SimpleDwQueryBuilder withSelectColumn(SelectColumnBuilder selectColumnBuilder) {
        if (this.selectColumns == null) {
            this.selectColumns = new LinkedHashSet<>();
        }

        this.selectColumns.add(selectColumnBuilder.build());
        return this;
    }

    public SimpleDwQueryBuilder withSelectColumns(Set<SelectColumn> selectColumns) {
        this.selectColumns = new LinkedHashSet<>(selectColumns);
        return this;
    }

    public SimpleDwQueryBuilder withGroupBy(GroupBy groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    public SimpleDwQueryBuilder withGroupBy(GroupByConditionBuilder groupByConditionBuilder) {
        this.groupBy = groupByConditionBuilder.build();
        return this;
    }

    public SimpleDwQueryBuilder withWhereConditionGroup(WhereConditionGroup whereConditionGroup) {
        this.whereConditionGroup = whereConditionGroup;
        return this;
    }

    public SimpleDwQueryBuilder withWhereConditionGroup(WhereConditionGroupBuilder whereConditionGroup) {
        this.whereConditionGroup = whereConditionGroup.build();
        return this;
    }

    public SimpleDwQueryBuilder withCombination(DwQueryCombination combination) {
        if (this.combineWith == null) {
            this.combineWith = new LinkedHashSet<>();
        }

        this.combineWith.add(combination);
        return this;
    }

    public SimpleDwQueryBuilder withCombination(DwQueryCombinationBuilder combinationBuilder) {
        if (this.combineWith == null) {
            this.combineWith = new LinkedHashSet<>();
        }

        this.combineWith.add(combinationBuilder.build());
        return this;
    }

    public SimpleDwQueryBuilder withCombinations(Set<DwQueryCombination> combinations) {
        this.combineWith = combinations;
        return this;
    }

    public SimpleDwQueryBuilder withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public SimpleDwQuery build() {
        return new SimpleDwQuery(selectColumns, tableName, groupBy, whereConditionGroup, combineWith);
    }
}
