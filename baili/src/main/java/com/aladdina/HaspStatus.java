/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   HaspStatus.java

package com.aladdina;


public class HaspStatus
{

    public HaspStatus()
    {
    }

    public static void Init()
    {
        String s = System.getProperty("os.name");
        String s1 = System.getProperty("os.arch");
        try
        {
            if(s.indexOf("Windows") != -1)
            {
                if(s1.equals("x86"))
                    System.loadLibrary(runtime_library_x86_windows);
                else
                if(s1.equals("x86_64") || s1.equals("amd64"))
                {
            		System.out.println(System.getProperty("java.library.path"));
                    String root_path = "c:\\irimdlls\\";
                    String base_path = root_path + "sentinelLDK/";
                    System.load(base_path + "hasp_windows_x64_37131.dll");
                    System.load(base_path + "HASPJava_x64.dll");
                }
                else
                if(s1.equals("ia64"))
                    System.loadLibrary(runtime_library_ia64_windows);
                else
                    System.loadLibrary(runtime_library_x86_windows);
            } else
            if(s.indexOf("Linux") != -1)
            {
                if(s1.equals("x86"))
                    System.loadLibrary(runtime_library_x86_linux);
                else
                if(s1.equals("x86_64") || s1.equals("amd64"))
                    System.loadLibrary(runtime_library_x64_linux);
                else
                if(s1.equals("ia64"))
                    System.loadLibrary(runtime_library_ia64_linux);
                else
                if(s1.equals("ppc"))
                    System.loadLibrary(runtime_library_ppc_linux);
                else
                if(s1.equals("ppc64"))
                    System.loadLibrary(runtime_library_ppc64_linux);
                else
                    System.loadLibrary(runtime_library_x86_linux);
            } else
            if(s.indexOf("Mac OS X") != -1)
                System.loadLibrary(runtime_library_ub_darwin);
            else
                System.loadLibrary(runtime_library_ub_darwin);
        }
        catch(UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            if(unsatisfiedlinkerror.getMessage().indexOf("already loaded in another classloader") == -1)
                throw unsatisfiedlinkerror;
        }
    }

    public static final int HASP_STATUS_OK = 0;
    public static final int HASP_MEM_RANGE = 1;
    public static final int HASP_INV_PROGNUM_OPT = 2;
    public static final int HASP_INSUF_MEM = 3;
    public static final int HASP_TMOF = 4;
    public static final int HASP_ACCESS_DENIED = 5;
    public static final int HASP_INCOMPAT_FEATURE = 6;
    public static final int HASP_HASP_NOT_FOUND = 7;
    public static final int HASP_CONTAINER_NOT_FOUND = 7;
    public static final int HASP_TOO_SHORT = 8;
    public static final int HASP_INV_HND = 9;
    public static final int HASP_INV_FILEID = 10;
    public static final int HASP_OLD_DRIVER = 11;
    public static final int HASP_NO_TIME = 12;
    public static final int HASP_SYS_ERR = 13;
    public static final int HASP_NO_DRIVER = 14;
    public static final int HASP_INV_FORMAT = 15;
    public static final int HASP_REQ_NOT_SUPP = 16;
    public static final int HASP_INV_UPDATE_OBJ = 17;
    public static final int HASP_KEYID_NOT_FOUND = 18;
    public static final int HASP_INV_UPDATE_DATA = 19;
    public static final int HASP_INV_UPDATE_NOTSUPP = 20;
    public static final int HASP_INV_UPDATE_CNTR = 21;
    public static final int HASP_INV_VCODE = 22;
    public static final int HASP_ENC_NOT_SUPP = 23;
    public static final int HASP_INV_TIME = 24;
    public static final int HASP_NO_BATTERY_POWER = 25;
    public static final int HASP_NO_ACK_SPACE = 26;
    public static final int HASP_TS_DETECTED = 27;
    public static final int HASP_FEATURE_TYPE_NOT_IMPL = 28;
    public static final int HASP_UNKNOWN_ALG = 29;
    public static final int HASP_INV_SIG = 30;
    public static final int HASP_FEATURE_NOT_FOUND = 31;
    public static final int HASP_NO_LOG = 32;
    public static final int HASP_LOCAL_COMM_ERR = 33;
    public static final int HASP_UNKNOWN_VCODE = 34;
    public static final int HASP_INV_SPEC = 35;
    public static final int HASP_INV_SCOPE = 36;
    public static final int HASP_TOO_MANY_KEYS = 37;
    public static final int HASP_TOO_MANY_USERS = 38;
    public static final int HASP_BROKEN_SESSION = 39;
    public static final int HASP_REMOTE_COMM_ERR = 40;
    public static final int HASP_FEATURE_EXPIRED = 41;
    public static final int HASP_OLD_LM = 42;
    public static final int HASP_DEVICE_ERR = 43;
    public static final int HASP_UPDATE_BLOCKED = 44;
    public static final int HASP_TIME_ERR = 45;
    public static final int HASP_SCHAN_ERR = 46;
    public static final int HASP_STORAGE_CORRUPT = 47;
    public static final int HASP_NO_VLIB = 48;
    public static final int HASP_INV_VLIB = 49;
    public static final int HASP_SCOPE_RESULTS_EMPTY = 50;
    public static final int HASP_VM_DETECTED = 51;
    public static final int HASP_HARDWARE_MODIFIED = 52;
    public static final int HASP_USER_DENIED = 53;
    public static final int HASP_UPDATE_TOO_OLD = 54;
    public static final int HASP_UPDATE_TOO_NEW = 55;
    public static final int HASP_OLD_VLIB = 56;
    public static final int HASP_UPLOAD_ERROR = 57;
    public static final int HASP_INV_RECIPIENT = 58;
    public static final int HASP_INV_DETACH_ACTION = 59;
    public static final int HASP_TOO_MANY_PRODUCTS = 60;
    public static final int HASP_INV_PRODUCT = 61;
    public static final int HASP_UNKNOWN_RECIPIENT = 62;
    public static final int HASP_INV_DURATION = 63;
    public static final int HASP_CLONE_DETECTED = 64;
    public static final int HASP_UPDATE_ALREADY_ADDED = 65;
    public static final int HASP_HASP_INACTIVE = 66;
    public static final int HASP_NO_DETACHABLE_FEATURE = 67;
    public static final int HASP_NO_DEATCHABLE_FEATURE = 67;
    public static final int HASP_TOO_MANY_HOSTS = 68;
    public static final int HASP_REHOST_NOT_ALLOWED = 69;
    public static final int HASP_LICENSE_REHOSTED = 70;
    public static final int HASP_REHOST_ALREADY_APPLIED = 71;
    public static final int HASP_CANNOT_READ_FILE = 72;
    public static final int HASP_EXTENSION_NOT_ALLOWED = 73;
    public static final int HASP_DETACH_DISABLED = 74;
    public static final int HASP_REHOST_DISABLED = 75;
    public static final int HASP_DETACHED_LICENSE_FOUND = 76;
    public static final int HASP_RECIPIENT_OLD_LM = 77;
    public static final int HASP_SECURE_STORE_ID_MISMATCH = 78;
    public static final int HASP_DUPLICATE_HOSTNAME = 79;
    public static final int HASP_MISSING_LM = 80;
    public static final int HASP_FEATURE_INSUFFICIENT_EXECUTION_COUNT = 81;
    public static final int HASP_INCOMPATIBLE_PLATFORM = 82;
    public static final int HASP_HASP_DISABLED = 83;
    public static final int HASP_SHARING_VIOLATION = 84;
    public static final int HASP_NO_API_DYLIB = 400;
    public static final int HASP_INV_API_DYLIB = 401;
    public static final int HASP_INV_PARAM = 501;
    public static final int HASP_NO_EXTBLOCK = 600;
    public static final int HASP_INV_PORT_TYPE = 650;
    public static final int HASP_INV_PORT = 651;
    public static final int HASP_NOT_IMPL = 698;
    public static final int HASP_INT_ERR = 699;
    public static final int HASP_FIRST_HELPER = 2001;
    public static final int HASP_FIRST_HASP_ACT = 3001;
    public static String runtime_library_x86_windows = "HASPJava";
    public static String runtime_library_x64_windows = "HASPJava_x64";
    public static String runtime_library_ia64_windows = "HASPJava_ia64";
    public static String runtime_library_x86_linux = "HASPJava";
    public static String runtime_library_x64_linux = "HASPJava_x86_64";
    public static String runtime_library_ia64_linux = "HASPJava_ia64";
    public static String runtime_library_ppc_linux = "HASPJava_ppc";
    public static String runtime_library_ppc64_linux = "HASPJava_ppc64";
    public static String runtime_library_ub_darwin = "HASPJava";

}


/*
	DECOMPILATION REPORT

	Decompiled from: D:\eclipse source\NewTest\hasp-srm-api.jar
	Total time: 196 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/