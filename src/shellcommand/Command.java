package shellcommand;

public class Command {
	
	private String description;
	private String dos;
	private String shell;

	private boolean isDOS = System.getProperty("os.name").toLowerCase().startsWith("windows");
	
	//                                                          Action                									   DOS Command                Shell command
	public static Command List_Directory_REVERSE_TIME_MODIFICATION 
										= new Command("List directory contents by reverse time of modification/creation",	"DIR *.* /o-d",            	 "ls -tr" );
	public static Command List_Directory = new Command("List directory contents",    									 	"DIR",                       "ls"     );
	public static Command List_File_Size = new Command("List files and size",    											"DIR *.* /v /os",            "ls -ls" );
	public static Command List_Directory_SubDirectory = new Command("List directory/sub-directorycontents recursively", 	"DIR *.* /v /os",            "ls -R"  );
	public static Command Change_Directory = new Command("Change directory",    											"CD",            			 "cd"     );
	public static Command New_Directory = new Command("Make a new directory",    											"MKDIR",            		 "mkdir"  );
	public static Command Create_Link 		= new Command("Create a file or directory link",    							"ASSIGN",            	     "ln"     );
	public static Command Remove_Directory = new Command("Remove a directory",    											"RMDIR",                     "rmdir"  );
	public static Command Display_Current_Directory = new Command("Display directory location",    							"CHDIR",                     "pwd"    );
	public static Command Remove_File = new Command("Remove file",    														"DEL",            			 "rm -iv" );
	public static Command Copy_File = new Command("Copy a file",    														"COPY",             		 "cp -piv");
	public static Command Rename_File = new Command("Rename/move a file",    												"RENAME",         			 "mv -iv" );
	public static Command Move_File = new Command("Move a file",    														"MOVE",         			 "mv -iv" );
	public static Command Copy_File_Recursivly = new Command("Copy a file recursivly",    									"XCOPY",         			 "cp -R"  );
	public static Command Clear_Screen = new Command("Clear screen",    													"CLS",         				 "clear"  );
	public static Command Exit = new Command("Exit shell or command",    													"EXIT",         			 "exit"   );
	public static Command Find_In_File = new Command("Look for a word in files",    										"FIND",         			 "grep"   );
	public static Command Compare_2_Files = new Command("Compare two files and show differences",    						"COMP",         			 "diff"   );
	public static Command List_All_Environment_Variables = new Command("List all environement variables",    				"SET",         			 "set and env");
	public static Command Display_Text_Screen = new Command("Display text in screen",    									"ECHO",         			 "echo"   );
	public static Command Host_Name = new Command("Print host name of computer",    									    "HOSTNAME",         	   "hostname" );
	public static Command Reboot = new Command("Reboot system",    															"REBOOT",            "shutdown -r now");
	public static Command Service_Start = new Command("Start service/daemon.",    									        "NET START",   "service+ ServiceName +start"  );
	public static Command Service_Stop = new Command("Stop service/daemon.",    									        "NET STOP", "service+ ServiceName +stop"  );
	public static Command List_Services = new Command("List service",    									                "NET HELP START",  "chkconfig --list |grep on"  );
	public static Command Ping_Service = new Command("Send packets to a network host",    									"PING",         			   "ping"  );
	public static Command Execute = new Command("Execute script from within batch shell.",    								"CALL",       "source script (cshrc)"  );
	public static Command Edit_File = new Command("Edit files.",    														"EDIT",         			     "vi"  );
	public static Command List_History = new Command("List command history",    											"DOSKEY /h",         	    "history"  );
	public static Command IP_Config = new Command("Display configure network interface",    								"IPCONFIG",         	   "ifconfig"  );
	public static Command IP_Route = new Command("Print routing table.",    												"ROUTE PRINT",         	   "route -n"  );
	
	
	
	private Command(String description, String dos, String shell) {
		this.description = description;
		this.dos = dos;
		this.shell = shell;
	}

	public String getDescription() {return description;}

	public String get() {
		if(isDOS) {
			return this.dos;
		}else {
			return this.shell;
		}
	}


}
