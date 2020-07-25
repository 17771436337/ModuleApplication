package cai.project.module.accountmanagement;

public final class Constants {

    public final static int ACCOUNT_START  = 0x0001;//常数起始点

    public final static int EDITOR = ACCOUNT_START+1;//编辑状态

    public final static int ADD = EDITOR+1;//添加状态

    public final static int CHECK = ADD + 1;//查看状态

    public final static int ACCOUNT_MESSAGE_ERASABLE = 0x0010;//账号信息可删除状态

    public final static int ACCOUNT_MESSAGE_NOT_DELETE =ACCOUNT_MESSAGE_ERASABLE +1;//账号信息不可删除状态


    // 7.0以上得读取地址标记
    public static final String APP_PROVIDER = "cai.project.module.accountmanagement";

    public static final String AES_IV = "C72723";

    public static final String AES_KEY = "a123";
}
