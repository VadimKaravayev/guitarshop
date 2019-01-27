package com.epam.preprod.karavayev.db;

public class SqlBuilder {

    private static final String SELECT = "SELECT";
    private static final String FROM = "FROM";
    private static final String JOIN = "JOIN";
    private static final String WHERE = "WHERE";
    private static final String ORDER_BY = "ORDER BY";
    private static final String LIMIT = "LIMIT";
    private static final String AND = "AND";
    private static final String IN = "IN";
    private static final String ON = "ON";

    private StringBuilder query;

    public SqlBuilder() {
        query = new StringBuilder();
    }

    public SqlBuilder select(String... columns) {
        query.append(SELECT).append(" ");
        for (String column : columns) {
            query.append(column).append(",");
        }
        query.setLength(query.length() - 1);
        return this;
    }

    public SqlBuilder from(String table) {
        query.append(" ").append(FROM).append(" ").append(table);
        return this;
    }

    public SqlBuilder join(Join join, String destTable, String condition) {
        query.append(" ")
                .append(join.getType())
                .append(" ")
                .append(JOIN)
                .append(" ")
                .append(destTable)
                .append(" ")
                .append(ON)
                .append(" ")
                .append(condition);
        return this;
    }

    public SqlBuilder where(String condition) {
        query.append(" ").append(WHERE).append(" ").append(condition);
        return this;
    }

    public SqlBuilder and(String condition) {
        query.append(" ").append(AND).append(" ").append(condition);
        return this;
    }

    public SqlBuilder in(String column, String... params) {
        if (params.length > 0) {
            query.append(" ").append(column).append(" ").append(IN).append("(");
            for (String param : params) {
                query.append(param).append(",");
            }
            query.setLength(query.length() - 1);
            query.append(")");
        }
        return this;
    }

    public SqlBuilder orderBy(String condition) {
        query.append(" ").append(ORDER_BY).append(" ").append(condition);
        return this;
    }

    public SqlBuilder limit(int from, int count) {
        query.append(" ").append(LIMIT).append(" ").append(from).append(",").append(count);
        return this;
    }

    public String toQuery() {
        return query.toString();
    }
}
