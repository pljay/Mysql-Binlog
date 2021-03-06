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
package cn.wizool.android.com.google.code.or.io;

import java.io.IOException;

import cn.wizool.android.com.google.code.or.common.glossary.UnsignedLong;
import cn.wizool.android.com.google.code.or.common.glossary.column.BitColumn;
import cn.wizool.android.com.google.code.or.common.glossary.column.StringColumn;

/**
 * 
 * @author Jingqi Xu
 */
public interface XInputStream {
	
	/**
	 * 
	 */
	void close() throws IOException;
	
	int available() throws IOException;
	
	boolean hasMore() throws IOException;
	
	void setReadLimit(int limit) throws IOException;
	
	/**
	 * 
	 */
	long skip(long n) throws IOException;
	
	int readInt(int length) throws IOException;
	
	long readLong(int length) throws IOException;
	
	byte[] readBytes(int length) throws IOException;
	
	BitColumn readBit(int length) throws IOException;
	
	int readSignedInt(int length) throws IOException;
	
	long readSignedLong(int length) throws IOException;
	
	UnsignedLong readUnsignedLong() throws IOException;
	
	StringColumn readLengthCodedString() throws IOException;
	
	StringColumn readNullTerminatedString() throws IOException;
	
	StringColumn readFixedLengthString(int length) throws IOException;
	
	int readInt(int length, boolean littleEndian) throws IOException;
	
	long readLong(int length, boolean littleEndian) throws IOException;
	
	BitColumn readBit(int length, boolean littleEndian) throws IOException;
}
