package com.mkyong.rest;

import com.mkyong.rest.client.NetClientGet;
import com.mkyong.rest.client.NetClientPost;

public class ExecuteMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NetClientPost.netClientPost();
		NetClientGet.netClientGet();
	}
}