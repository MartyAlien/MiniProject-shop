package com.neuedu.japidocs;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Properties;

public class JdocApi {
    public static void main(String[] args) {

        DocsConfig config = new DocsConfig();
        String project = System.getProperty("user.dir");
        System.out.println(project);
        config.setProjectPath(project); // 项目根目录
        config.setProjectName("微信小程序-项目接口文档(东软睿乐购)-powered by 金山"); // 项目名称
        config.setApiVersion("V1.0" );       // 声明该API的版本
        config.setDocsPath(project+"\\docs"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }

}
