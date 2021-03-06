package tmall.display;


import tmall.display.command.Command;
import tmall.display.dispatcher.Dispatcher;
import tmall.display.expression.Context;

import java.util.ArrayList;

public class FrontController {
    private final Dispatcher dispatcher;
    private final Context context;
    private ArrayList<Command> orders;
    private static FrontController frontController;

    private FrontController() {
        dispatcher = Dispatcher.getDispatcher();
        context = new Context();
    }

    /**
     * 本方法用于获取FrontController对象
     * @return 一个FrontController对象
     */
    public static FrontController getFrontController() {
        if(frontController==null){
            frontController=new FrontController();
        }
        return frontController;
    }

    /**
     * 本方法用于派发单个命令，使用逻辑为：根据传入的命令对象获取其命令类型（一个命令对应一个业务逻辑或者是页面），并调用其execute方法获得需要展示的数据，然后通过页面调度器展示
     * @param command 需要派发的命令
     */
    public void dispatchSingleCommand(Command command){
        if (command !=null){
            Object[] arg = command.execute();
            String orderType = command.getCommandName();
            dispatcher.dispatch(orderType,arg);
//            // 解释命令，返回值为需要调用的页面和需要展示的参数，args[0]为页面名称
//            Object[] args = context.interpret(command);
//            String viewName =(String) args[0];
//            // 若数组为空，则不需要进行展示，只是做操作
//            if (args != null){
//                dispatcher.dispatch(viewName,args);
//            }
        }
    }

//    /**
//     * 本方法用于派发orders数组中记录的所用命令
//     */
//    public void dispatchAllCommands(){
//        for (Command o:orders){
//            dispatchSingleCommand(o);
//        }
//    }

    /**
     * 本方法用于保存命令
     * @param command 需要保存的命令
     * @return 1为保存成功，-1为保存失败
     */
    public int addOrder(Command command){
        if (command !=null){
            orders.add(command);
            return 1;
        }else
            return -1;
    }
}
