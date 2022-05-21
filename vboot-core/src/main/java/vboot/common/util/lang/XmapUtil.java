package vboot.common.util.lang;

import java.util.HashMap;
import java.util.Map;
public class XmapUtil
{
    public static String getName(String id)
    {
        String[] arr = id.split("/");
        String back = "";
        for (int i = 0; i < arr.length; i++)
        {
            if (!"".equals(arr[i]))
            {
                String mapValue = map.get(arr[i]);
                if (mapValue != null)
                {
                    back += map.get(arr[i]) + "-";
                }
                else
                {
                    back += arr[i] + "-";
                }
            }
        }
        return back.substring(0, back.length() - 1);
    }

    public static Map<String, String> map = new HashMap<String, String>();

    static
    {
        //a
        map.put("admin", "管理员");
        map.put("address", "地址");
        map.put("att", "附件");
        map.put("auth", "认证");
        map.put("actual", "实际");
        //b
        map.put("bar", "块");
        //c
        map.put("choose", "选择");
        map.put("customer", "客户");
        map.put("config", "配置");
        map.put("choose_data", "选择数据");
        //d
        map.put("demo", "样例");
        //e
        map.put("edit", "编辑");
        map.put("edit_save", "编辑保存");

        //f
        map.put("file", "文件");
        map.put("flow", "流程");

        //g
        map.put("group", "组");

        //l
        map.put("log", "日志");
        map.put("login", "登入");

        //i
        map.put("index", "主页");
        map.put("info", "信息");
        //t
        map.put("task", "任务");
        map.put("type", "分类");

        //m
        map.put("mail", "邮箱");
        map.put("main", "主信息");
        map.put("move", "移动");
        map.put("mobile", "移动端");
        //n
        map.put("node", "节点");

        //o
        map.put("org", "组织架构");
        //p
        map.put("password", "密码");
        map.put("product", "产品");
        map.put("project", "项目");
        map.put("pp", "生产");
        //q
        map.put("quote", "报价");

        //k

        //l
        map.put("line", "线");
        map.put("list", "列表");
        map.put("list_data", "列表数据");
        map.put("list_dele", "列表删除");
        map.put("layout", "扶梯排布");

        //r
        map.put("register", "注册");
        map.put("report", "报表");
        map.put("perm", "权限");
        map.put("role", "角色");
        map.put("scheme", "方案");

        //s
        map.put("save", "保存");
        map.put("sd", "销售");
        map.put("send", "发送");
        map.put("set", "设置");
        map.put("show", "展示");
        map.put("stock", "股票");

        //t
        map.put("task", "任务");
        map.put("template", "模块");
        map.put("test", "测试");
        map.put("tool", "工具");
        map.put("tree", "树");
        map.put("turn", "转换");
        map.put("tree_data", "树数据");
        map.put("tree_move", "树移动");
        map.put("tree_remove", "树删除");

        //v
        map.put("view", "查看");

        //w
        map.put("warn", "警告");
        map.put("weather", "天气");
        map.put("write", "撰写");

    }



}
