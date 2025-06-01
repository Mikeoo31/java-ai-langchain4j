package com.ithui.ai.langchain4j.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ithui.ai.langchain4j.entity.Appointment;
import org.springframework.stereotype.Service;


public interface AppointmentService extends IService<Appointment> {
    /**
     * 查询预约信息
     * @param appointment
     * @return
     */
    Appointment getOne(Appointment appointment);
}
