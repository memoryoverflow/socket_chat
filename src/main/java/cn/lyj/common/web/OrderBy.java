package cn.lyj.common.web;

/**
 * <p>
 * 排序字段
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
public class OrderBy
{
    private String[] columns;
    private Direction direction;

    public OrderBy(OrderBy.Direction direction, String... properties)
    {
        this.columns = properties;
        this.direction = direction;
    }

    public String[] getColumns()
    {
        return columns;
    }

    public void setColumns(String[] columns)
    {
        this.columns = columns;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public enum Direction
    {
        ASC,
        DESC;

        private Direction()
        {
        }

        public boolean isAscending()
        {
            return this.equals(ASC);
        }

        public boolean isDescending()
        {
            return this.equals(DESC);
        }
    }
}
