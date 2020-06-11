package cai.project.module.common_utils.subutils.http;

import java.io.IOException;

public interface Interceptor {

    Response intercept(Chain chain) throws IOException;
}
