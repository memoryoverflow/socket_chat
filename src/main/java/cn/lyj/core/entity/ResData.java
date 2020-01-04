package cn.lyj.core.entity;

public class ResData
{
    private String fromUserId;
    private String fromUserName;
    private String content;
    private String data;

    public ResData(String fromUserId, String fromUserName, String content, String data)
    {
        this.fromUserId = fromUserId;
        this.fromUserName = fromUserName;
        this.content = content;
        this.data = data;
    }

    public ResData(String fromUserId, String content)
    {
        this.fromUserId = fromUserId;
        this.content = content;
    }

    public ResData(String fromUserId, String fromUserName, String content)
    {
        this.fromUserId = fromUserId;
        this.fromUserName = fromUserName;
        this.content = content;
    }

    public String getFromUserId()
    {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId)
    {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName()
    {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName)
    {
        this.fromUserName = fromUserName;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }
}