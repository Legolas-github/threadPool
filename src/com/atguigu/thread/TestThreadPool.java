package com.atguigu.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
	public static void main(String[] args) {
		system.out.print("123");
		ScheduledExecutorService service=Executors.newScheduledThreadPool(5);
		ScheduledFuture<Integer> result=null;
		try {
			//一池5线程工作,有20个任务
			for(int i=1;i<=20;i++){
				
				result=service.schedule(new Callable<Integer>() {
					
					@Override
					public Integer call() throws Exception {
						Thread.sleep(200);
						System.out.print(Thread.currentThread().getName()+"---->");
						
						
						return new Random().nextInt(50);
					}
				},3,TimeUnit.SECONDS);
				System.out.println(result.get());
			}
		} catch (Exception e) {
		}finally{
			service.shutdown();
		}
		
	}

	private static void getThreadPool() {
		//	ExecutorService threadPool=Executors.newFixedThreadPool(5);//一池5线程
		//	ExecutorService threadPool=Executors.newSingleThreadExecutor();//一池1线程
			ExecutorService threadPool=Executors.newCachedThreadPool();//一池按需要随机线程
			
			Future<Integer> result=null;
			try {
				//一池5线程工作,有20个任务
				for(int i=1;i<=20;i++){
					
					result=threadPool.submit(new Callable<Integer>() {
						
						@Override
						public Integer call() throws Exception {
							Thread.sleep(200);
							System.out.print(Thread.currentThread().getName()+"---->");
							
							
							return new Random().nextInt(50);
						}
					});
					System.out.println(result.get());
				}
			} catch (Exception e) {
			}finally{
				threadPool.shutdown();
			}
	}
}
