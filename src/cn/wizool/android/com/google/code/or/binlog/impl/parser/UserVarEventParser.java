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
package cn.wizool.android.com.google.code.or.binlog.impl.parser;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.wizool.android.com.google.code.or.binlog.BinlogEventV4Header;
import cn.wizool.android.com.google.code.or.binlog.BinlogParserContext;
import cn.wizool.android.com.google.code.or.binlog.UserVariable;
import cn.wizool.android.com.google.code.or.binlog.impl.event.UserVarEvent;
import cn.wizool.android.com.google.code.or.binlog.impl.variable.user.UserVariableDecimal;
import cn.wizool.android.com.google.code.or.binlog.impl.variable.user.UserVariableInt;
import cn.wizool.android.com.google.code.or.binlog.impl.variable.user.UserVariableReal;
import cn.wizool.android.com.google.code.or.binlog.impl.variable.user.UserVariableRow;
import cn.wizool.android.com.google.code.or.binlog.impl.variable.user.UserVariableString;
import cn.wizool.android.com.google.code.or.io.XInputStream;

/**
 * 
 * @author Jingqi Xu
 */
public class UserVarEventParser extends AbstractBinlogEventParser {
	//
	private static final Logger LOGGER = LoggerFactory.getLogger(UserVarEventParser.class);

	/**
	 * 
	 */
	public UserVarEventParser() {
		super(UserVarEvent.EVENT_TYPE);
	}

	/**
	 * 
	 */
	public void parse(XInputStream is, BinlogEventV4Header header, BinlogParserContext context)
	throws IOException {
		final UserVarEvent event = new UserVarEvent(header);
		event.setVarNameLength(is.readInt(4));
		event.setVarName(is.readFixedLengthString(event.getVarNameLength()));
		event.setIsNull(is.readInt(1));
		if(event.getIsNull() == 0) {
			event.setVarType(is.readInt(1));
			event.setVarCollation(is.readInt(4));
			event.setVarValueLength(is.readInt(4));
			event.setVarValue(parseUserVariable(is, event));
		}
		context.getEventListener().onEvents(event);
	}

	/**
	 * 
	 */
	protected UserVariable parseUserVariable(XInputStream is, UserVarEvent event) 
	throws IOException {
		final int type = event.getVarType();
		switch(type) {
		case UserVariableDecimal.TYPE: return new UserVariableDecimal(is.readBytes(event.getVarValueLength()));
		case UserVariableInt.TYPE: return new UserVariableInt(is.readLong(event.getVarValueLength()), is.readInt(1));
		case UserVariableReal.TYPE: return new UserVariableReal(Double.longBitsToDouble(is.readLong(event.getVarValueLength())));
		case UserVariableRow.TYPE: return new UserVariableRow(is.readBytes(event.getVarValueLength()));
		case UserVariableString.TYPE: return new UserVariableString(is.readBytes(event.getVarValueLength()), event.getVarCollation());
		default: LOGGER.warn("unknown user variable type: " + type); return null;
		}
	}
}
