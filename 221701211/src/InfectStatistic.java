import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;




class InfectStatistic 
{
	private static final int provinceNum = 32;
	public static String provinces[] = {
			"全国","安徽","北京","重庆","福建","甘肃","广东","广西","贵州","海南","河北","河南",
			"黑龙江","湖北","湖南","吉林","江苏","江西","辽宁","内蒙古","宁夏","青海","山东","山西",
			"陕西","上海","四川","天津","西藏","新疆","云南","浙江"
	};
	
    //省类，用于体现各种信息
    public class Province 
    {

        String provinceName; 
        int ip; 
        int sp;
        int cure;
        int dead;

        Province(String provinceName, int ip, int sp, int cure, int dead) {
            this.provinceName = provinceName;
            this.ip = ip;
            this.sp = sp;
            this.cure = cure;
            this.dead = dead;
        }

        public void increaseIp(int newIpNum) {
            ip += newIpNum;
        }

        public void decreaseIp(int ipNum) {
            ip -= ipNum;
        }

        public void increaseSp(int newSpNum) {
            sp += newSpNum;
        }
        
        public void decreaseSp(int spNum) {
            sp -= spNum;
        }

        public void increaseCure(int newCureNum) {
            cure += newCureNum;
        }

        public void increaseDead(int newDeadNum) {
            dead += newDeadNum;
        }
        
        public String getProvinceName() {
            return provinceName;
        }

        public long getIp() {	
            return ip;
        }

        public long getSp() {
            return sp;
        }

        public long getCure() {
            return cure;
        }

        public long getDead() {
            return dead;
        }

        //输出
        public String getAllResult() 
        {	
        	File outFile = new File(outPath);
            FileWriter writer = null;    
            try 
            {
                writer = new FileWriter(outFile);
                for(String province : statistics.keySet()) 
                {   
                    if(!outProvince.contains(province)) 
                    {
                        List<Integer> data = statistics.get(province);
                        writer.write(province + "    ");
                    }
                    else if  
                    {
                        String s = GetStringByType(typeparam,"全国");
                        writer.write(s);
                        for (int i = 0; i < 31; i++) 
                        {
                            if (IsVisited[i]) 
                            {
                                String st = GetStringByType(typeparam,provinces[i]);
                                writer.write(st);
                            }
                        }
                    }
                    List<Integer> data = statistics.get(province);
                    writer.write(province + "    ");
                    for(String type : outType.keySet()) 
                    {
                        writer.write(type + data.get(outType.get(type)) + "人    ");
                    }
                    writer.write("\n");
                }
                writer.write("//该文档并非真实数据，仅供测试使用\n");
                writer.flush();
            } 
            catch (Exception e) 
            {
                throw new Lib.Exit("\"out\" " + e.getMessage());
            } 
            finally 
            {
                try 
                {
                    if (writer != null) 
                    {
                        writer.close();   
                    }
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        }      
    } 
    
    //初始化
    private static void initProvince(Province province[]){
    	for(int i = 0;i < provinceNum;i++){
    		province[i] = new Province();
    		province[i].provinceName = province[i];
    		province[i].ip = 0;
    		province[i].sp = 0;
    		province[i].cure = 0;
    		province[i].dead = 0;
    	}
    }	
    
    //命令操作
    public void GetCommand(String[] CommandSource)
	{
		int CommandLength = CommandSource.length;
		if(CommandSource[0].equals("list"))
		{
			this.Command = 1;
		}
		else
		{
			System.out.println(CommandSource[0] + " 不能被解析为合法命令");
		}
		for(int i = 1 ; i < CommandLength ; i++)
		{
			if(CommandSource[i].equals("-log"))
			{
				this.IsLog = true;
				this.Log = CommandSource[i+1];
				i++;
			}

			else if(CommandSource[i].equals("-out"))
			{
				this.IsOut = true;
				this.Out = CommandSource[i+1];
				i++;
			}

			else if(CommandSource[i].equals("-date"))
			{
				this.IsDate = true;
				this.Date = CommandSource[i+1];
				i++;
			}
			
			else if(CommandSource[i].equals("-province"))
			{
				this.IsProvince = true;
				int j = 0;
				while(i+1<CommandSource.length&&!Pattern.matches("-.*", CommandSource[i+1]))
				{
					this.Province[j] = CommandSource[i+1];
					i++;
					j++;
				}
			}
			else if(CommandSource[i].equals("-type"))
			{
				this.IsType = true;
				int j = 0;
				while(i+1<CommandSource.length && !Pattern.matches("-.*", CommandSource[i+1]))
				{
					this.Type[j] = CommandSource[i+1];
					i++;
					j++;
				}
			}
			else 
				System.out.println("命令中包含不合法参数" + CommandSource[i]);
		}
		if(!this.IsLog) 
		{
			System.out.println("缺少必要的log参数");
			if(!this.IsOut)
				System.out.println("缺少必要的out参数");
			System.exit(1);
		}
		if(!this.IsOut) 
		{
			System.out.println("缺少必要的out参数");
			if(!this.IsLog)
				System.out.println("缺少必要的log参数");
			System.exit(1);
		}
	}
	
	//判断传入的字符串是否合法日期，false为不合法
	public boolean isCorrectDate(String date) 
	{
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		try {
			sd.setLenient(false);
			sd.parse(date);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

	//单行输入
	static class OpLineStringMethods
	{
		public static int getNumber(String string) 
		{
			for (int i=0,len=string.length(); i < len; i++) 
			{
				if (Character.isDigit(string.charAt(i))) 
				{
					;
				} 
				else 
				{
           	      	string = string.substring(0, i);
           	      	break;
				}
			}
			return Integer.parseInt(string);
      }

		public static String[] getNeedModifyProvinceNames(String[] strings) 
		{
			int len = strings.length;
			String[] resStrings = new String[2];
			if (len == 3 || len == 4) 
			{
				resStrings[0] = strings[0];
				resStrings[1] = "";
			} 
			else if (len == 5) 
			{
				resStrings[0] = strings[0];
				resStrings[1] = strings[3];
			}
			return resStrings;
		}
      
		public static boolean isAnnotation(String lineString) 
		{
			if (lineString.equals("") || lineString.charAt(0) == '/' && lineString.charAt(1) == '/') 
			{
				return true;
			} 
			else 
			{
				return false;
			}
		}
	}

	//list命令类
	public static void CmdList ()
	{
		public void doCmd(Param pm) throws Exception 
		{
			FileHandle l=new FileHandle();
			String[] require=null;
			if (!pm.isLog()||(!pm.isLog())) throw new Exception("-log -out为必需项");
	    	if (!pm.isType()) pm.setTypeValue();
			if (!pm.isDate()) pm.setDateValue("");
	    	if (pm.isProvince()) require=pm.getProvinceValue();
	    	l.dealFile(pm.getLogValue(),pm.getDateValue(),require);
	    	l.writeFile(pm.getOutValue(),pm.getTypeValue());
		}
	}
	
	public List<LogResult> toFormatPro(String[] pro, List<LogResult> all) 
	{
		HashMap<String,LogResult> target=new HashMap<>();
		for (String s : pro) {
			LogResult lr = new LogResult(s);
			target.put(s, lr);
		}
		for (LogResult lr:all) {
			String tmp=lr.getProvince();
			if (target.containsKey(tmp)) 
			{
				LogResult temp=new LogResult(tmp);
                temp.setIp(target.get(tmp).getIp()+lr.getIp());
                temp.setSp(target.get(tmp).getSp()+lr.getSp());
                temp.setCure(target.get(tmp).getCure()+lr.getCure());
                temp.setDead(target.get(tmp).getDead()+lr.getDead());
                target.put(tmp,temp);
			}
		}
		List<LogResult> newList=new ArrayList<>();
        for (String tmp:target.keySet()) 
        {
            newList.add(target.get(tmp));
        }
        return newList;
	}
	
	public List<LogResult> ListSort (List<LogResult> list) 
	{
		Collections.sort(list,new Comparator<LogResult>() 
		{
			public int compare(LogResult r1,LogResult r2) 
			{
				return r1.getIndex(r1.getProvince())-r2.getIndex(r2.getProvince());
			}
		});
		return list;
	}

    public static void main(String[] args) {        
    	InfectStatistic infectStatistic = new InfectStatistic();
        try 
        {
            if(args.length == 0)
           	{               
           		throw new Lib.Exit("请按照提示输入命令");
           	}
           	infectStatistic.takeOrder(args[0]);
           	infectStatistic.placeOrder(args);
        }
        catch (Lib.Exit exit)
        {
            System.out.println(exit.getMessage());
        }
    }    
}
