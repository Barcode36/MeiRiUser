package com.example.meiriuser.event;

/**
 *
 */

public class OrderPriceEvent implements IBus.IEvent{
    @Override
    public int getTag() {
        return 0;
    }

    private int count;
    private double price;

    public OrderPriceEvent(int count,double price) {
        this.count=count;
        this.price=price;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    /*  BusProvider.getBus().post(new OrderPriceEvent(type));


     BusProvider.getBus().toObservable(OrderPriceEvent.class)
                .subscribe(new Action1<OrderPriceEvent>() {
        @Override
        public void call(OrderPriceEvent newMessageEvent) {
            //返回到编辑模式
            onBackToInputMode();
        }
    });*/

}
