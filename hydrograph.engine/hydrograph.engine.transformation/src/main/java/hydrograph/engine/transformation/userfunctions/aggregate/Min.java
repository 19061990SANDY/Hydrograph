/*******************************************************************************
 * Copyright 2017 Capital One Services, LLC and Bitwise, Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 *******************************************************************************/
package hydrograph.engine.transformation.userfunctions.aggregate;

import hydrograph.engine.transformation.userfunctions.base.AggregateTransformBase;
import hydrograph.engine.transformation.userfunctions.base.ReusableRow;

import java.util.ArrayList;
import java.util.Properties;

public class Min implements AggregateTransformBase {
	
	Comparable minVal;

	@Override
	public void prepare(Properties props, ArrayList<String> inputFields,
			ArrayList<String> outputFields, ArrayList<String> keyFields) {
		minVal=null;

	}

	@Override
	public void aggregate(ReusableRow input) {
		if (minVal==null){
			minVal=input.getField(0);
		}else{
			if (minVal.compareTo(input.getField(0)) >0){
				minVal=input.getField(0);
			}
		}

	}

	@Override
	public void onCompleteGroup(ReusableRow output) {
		output.setField(0, minVal);
		minVal=null;

	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

}
