package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DistroController
{
    public DistroController()
    {
        super();
    }

    private String os()
    {
        return System.getProperty("os.name");
    }

    public void exibeDistro()
    {
        if (!os().equals("Linux")) {
            System.out.println("Sistema NÃO é Linux");
        } else {
            try {
                Process p = Runtime.getRuntime().exec(new String[]{"cat", "/etc/os-release"});
                InputStreamReader output = new InputStreamReader(p.getInputStream());
                BufferedReader buffer = new BufferedReader(output);
                String line = buffer.readLine();
                StringBuffer versao = new StringBuffer();
                while (line != null) {
                    String[] split = line.split("=");
                    if (split[0].equals("NAME"))
                        versao.append("  Nome = " + split[1]);
                    if (split[0].equals("VERSION"))
                        versao.append("Versão = " + split[1]);
                    line = buffer.readLine();
                }
                System.out.println(versao.toString());
                buffer.close();
                output.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}