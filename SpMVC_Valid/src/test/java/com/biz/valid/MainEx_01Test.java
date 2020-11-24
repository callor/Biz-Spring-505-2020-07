package com.biz.valid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainEx_01Test {

	@Test
	void test() {
		MainEx_01 m = new MainEx_01();
		assertEquals(m.sum(), 100);
	}

}
