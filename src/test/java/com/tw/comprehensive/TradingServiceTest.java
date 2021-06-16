package com.tw.comprehensive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TradingServiceTest {
    @Test
    void should_verify_check_logNewTrade_method_when_call_createTrade_method() {
        // given
        TradeRepository tradeRepository = mock(TradeRepository.class);
        AuditService auditService = spy(AuditService.class);
        Trade trade = mock(Trade.class);
        TradingService tradingService = new TradingService(tradeRepository, auditService);
        // when
        when(tradeRepository.createTrade(trade)).thenReturn(anyLong());
        tradingService.createTrade(trade);
        // then
        verify(auditService, times(1)).logNewTrade(trade);
    }

    @Test
    void should_return_the_same_value_when_call_findTrade_method_then_query_by_findById() {
        // given
        TradeRepository tradeRepository = mock(TradeRepository.class);
        AuditService auditService = spy(AuditService.class);
        TradingService tradingService = new TradingService(tradeRepository, auditService);
        Trade trade = new Trade("test", "reference");
        // when
        when(tradeRepository.findById(anyLong())).thenReturn(trade);
        Trade result = tradingService.findTrade(anyLong());
        // then
        assertEquals(trade.getDescription(), result.getDescription());
        assertEquals(trade.getReference(), result.getReference());
    }

}