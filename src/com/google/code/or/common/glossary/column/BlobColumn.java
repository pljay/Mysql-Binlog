/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.code.or.common.glossary.column;

import java.io.UnsupportedEncodingException;

import com.google.code.or.common.glossary.Column;
import com.google.code.or.common.util.ToStringBuilder;

/**
 * 
 * @author Jingqi Xu
 */
public class BlobColumn implements Column {
	//
	private static final long serialVersionUID = 756688909230132013L;
	
	//
	private final byte[] value;

	/**
	 * 
	 */
	private BlobColumn(byte[] value) {
		this.value = value;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		String string = null;
		try {
			  string=new String(value,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
		
	}
	
	/**
	 * 
	 */
	public byte[] getValue() {
		return value;
	}
	
	/**
	 * 
	 */
	public static final BlobColumn valueOf(byte[] value) {
		return new BlobColumn(value);
	}
}
