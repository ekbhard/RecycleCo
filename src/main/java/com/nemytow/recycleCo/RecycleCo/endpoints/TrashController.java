package com.nemytow.recycleCo.RecycleCo.endpoints;
import com.nemytow.recycleCo.RecycleCo.api.messaging.MessagingApi;
import com.nemytow.recycleCo.RecycleCo.api.trash.UserTrashApi;
import com.nemytow.recycleCo.RecycleCo.dto.TrashData;
import com.nemytow.recycleCo.RecycleCo.dto.TrashStatistics;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trash")
@Valid
public class TrashController {

    @Autowired
    private final MessagingApi messagingApi;

    @Autowired
    private final UserTrashApi userTrashApi;

    public TrashController(MessagingApi messagingApi, UserTrashApi userTrashApi) {
        this.messagingApi = messagingApi;
        this.userTrashApi = userTrashApi;
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = {"application/json"})
    public void sendMessage(@RequestParam Long beanId, HttpServletRequest req) {
        messagingApi.sendMessage(beanId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = {"application/json"})
    protected List<TrashData> getUserTrash(){
        return userTrashApi.getTrashByUser();
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET, produces = {"application/json"})
    protected TrashStatistics getStatistics(){
        return userTrashApi.getTrashStatistic();
    }
}
