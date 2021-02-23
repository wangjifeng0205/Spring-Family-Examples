package net.wangjifeng.utils;

import java.util.Objects;

/**
 * @author: WJF
 * @date: 2020/7/29
 * @description: MysqlUtils
 */

public class MysqlUtils {

    public static void main(String[] args) {
        String[] columns = {"ID", "STUDENT_NAME", "STUDENT_NO", "SEX", "AGE", "CLASS"};
        System.out.println(insert("t_student", columns, 5, "李文进", "G030534", "男", 20, "G0305"));
        System.out.println(update("t_student", columns, "ID",5,5, "李文进", "G030536", "男", 20, "G0305"));
        System.out.println(delete("t_student", "ID", 5L));
    }

    /**
     * 新增一条数据
     * @param tableName 表名
     * @param columns 表字段名，按顺序写
     * @param args 表字段参数，按顺序写，和表字段对应起来
     * @return insert语句
     */
    public static String insert(String tableName, String[] columns, Object... args) {
        StringBuffer buffer = new StringBuffer("INSERT INTO");
        buffer.append(" ").append(tableName).append(" ").append("(");
        for (int i = 0; i < columns.length; i++) {
            buffer.append(columns[i]);
            if (i != columns.length - 1) {
                buffer.append(", ");
            }
        }
        buffer.append(")").append(" ").append("VALUES").append(" ").append("(");
        for (int i = 0; i < args.length; i++) {
            if (Objects.isNull(args[i])) {
                buffer.append("NULL");
            }
            if (args[i] instanceof Number) {
                buffer.append(args[i]);
            }
            if (args[i] instanceof String) {
                buffer.append("'").append(args[i]).append("'");
            }
            if (i != args.length - 1) {
                buffer.append(", ");
            }
        }
        buffer.append(")");
        return buffer.toString();
    }

    /**
     * 根据主键修改
     * @param tableName 表名
     * @param columns 表字段，按顺序写
     * @param whereColumn 唯一确定一条记录的字段
     * @param where 唯一确定一条记录字段的值
     * @param args 表字段参数，按顺序写，和表字段对应起来
     * @return update语句
     */
    public static String update(String tableName, String[] columns, String whereColumn, Object where, Object... args) {
        StringBuffer buffer = new StringBuffer("UPDATE ");
        buffer.append(tableName).append(" ").append("SET ");
        for (int i = 0; i < columns.length; i++) {
            buffer.append(columns[i]).append(" = ");
            if (Objects.isNull(args[i])) {
                buffer.append("NULL");
            }
            if (args[i] instanceof Number) {
                buffer.append(args[i]);
            }
            if (args[i] instanceof String) {
                buffer.append("'").append(args[i]).append("'");
            }
            if (i != args.length - 1) {
                buffer.append(", ");
            }
        }
        buffer.append(" WHERE ");
        buffer.append(whereColumn).append(" = ");
        if (where instanceof Number) {
            buffer.append(where);
        }
        if (where instanceof String) {
            buffer.append("'").append(where).append("'");
        }
        return buffer.toString();
    }


    /**
     * 根据唯一字段删除一条记录
     * @param tableName 表名
     * @param column 唯一字段
     * @param arg 唯一字段的值
     * @return delete语句
     */
    public static String delete(String tableName, String column, Object arg) {
        StringBuffer buffer = new StringBuffer("DELETE FROM ");
        buffer.append(tableName).append(" ");
        buffer.append("WHERE ");
        buffer.append(column).append(" = ");
        if (Objects.isNull(arg)) {
            buffer.append("NULL");
        }
        if (arg instanceof Number) {
            buffer.append(arg);
        }
        if (arg instanceof String) {
            buffer.append("'").append(arg).append("'");
        }
        return buffer.toString();
    }

}
