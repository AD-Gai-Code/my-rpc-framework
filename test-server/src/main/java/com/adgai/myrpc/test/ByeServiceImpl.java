package com.adgai.myrpc.test;

import com.adgai.myrpc.annotation.Service;
import com.adgai.myrpc.api.ByeService;

/**
 * @author ziyang
 */
@Service
public class ByeServiceImpl implements ByeService {

    @Override
    public String bye(String name) {
        return "bye, " + name;
    }
}
