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
package cn.wizool.android.com.google.code.or.common.glossary.column;

import java.text.SimpleDateFormat;

import cn.wizool.android.com.google.code.or.common.glossary.Column;

/**
 * 
 * @author Jingqi Xu
 */
public final class DateColumn implements Column {
	//
	private static final long serialVersionUID = 959710929844516680L;
	
	//
	private final java.sql.Date value;
	
	/**
	 * 
	 */
	private DateColumn(java.sql.Date value) {
		this.value = value;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.value);
		//return String.valueOf(this.value);
	}

	/**
	 * 
	 */
	public java.sql.Date getValue() {
		return this.value;
	}
	
	/**
	 * 
	 */
	public static final DateColumn valueOf(java.sql.Date value) {
		return new DateColumn(value);
	}
}
