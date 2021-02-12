package com.korbiak.api.service.impl;

import com.korbiak.api.messageSenders.EmailSender;
import com.korbiak.api.messageSenders.SmsSender;
import com.korbiak.api.model.ItemOrder;
import com.korbiak.api.repo.ItemOrderRepo;
import com.korbiak.api.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {
    private final ItemOrderRepo itemOrderRepo;
    private final SmsSender smsSender;
    private final EmailSender emailSender;

    @Override
    @Scheduled(cron = "0 0 12 * * *")
    public void sendPhoneMessage() {
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plus(1, ChronoUnit.DAYS);
        log.info("Start messaging");

        List<ItemOrder> orders = itemOrderRepo.findAll();
        for (ItemOrder order : orders) {
            LocalDate orderDate = order.getEndDate()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if (orderDate.equals(now)) {
                String message = "Добрий день. Хочемо вас попередити, що час оренди спорядження: '" +
                        order.getItem().getName() + "' спливає сьогодні:" + now;
                smsSender.sendSms(order.getCustomer().getPhone(), message);
                emailSender.sendEmail(order.getCustomer().getEmail(), message);
            }
            if (orderDate.equals(tomorrow)) {
                String message = "Добрий день. Хочемо вас попередити, що час оренди спорядження: '" +
                        order.getItem().getName() + "' спливає завтра:" + tomorrow;
                smsSender.sendSms(order.getCustomer().getPhone(), message);
                emailSender.sendEmail(order.getCustomer().getEmail(), message);
            }
        }
    }
}
