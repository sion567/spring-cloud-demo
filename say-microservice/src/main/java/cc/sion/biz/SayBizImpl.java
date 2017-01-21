package cc.sion.biz;

import org.springframework.stereotype.Service;

@Service
public class SayBizImpl implements ISayBiz {

    public String sayOk(String name) throws Exception {
        return "hello "+name;
    }

    public String sayFail(String name) throws Exception {
        throw new RuntimeException("sya bye bye~~");
    }
}
