import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.Channel;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class TestSSH {
    public static void main(String[] args) throws Exception {

        String userid = args[0];
        String host = args[1];
        String config_file=args[2];
        int port = 22;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String password = br.readLine();

        JSch.setLogger(new JSCHLogger());

        JSch jsch = new JSch();
        Session session;
        try {

            Properties props = new Properties();
            FileInputStream fis = new FileInputStream(config_file);
            props.load(fis);

            for(Map.Entry<Object, Object> e : props.entrySet()) {
                System.out.println(e);
            }

            session = jsch.getSession(userid, host, port);
            session.setPassword(password);

            session.setConfig(props);
            session.connect();


            Channel channel=session.openChannel("shell");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect(3*1000);

            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
