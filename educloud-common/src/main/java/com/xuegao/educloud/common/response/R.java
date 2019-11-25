/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xuegao.educloud.common.response;

import com.xuegao.educloud.common.constants.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 响应信息主体
 *
 * @param <T>
 * @author LIM
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String message;

	@Getter
	@Setter
	private boolean success;


	@Getter
	@Setter
	private T data;

	public static <T> R<T> ok() {
		return restResult(null, CommonConstants.SUCCESS, "SUCCESS", true);
	}

	public static <T> R<T> ok(T data) {
		return restResult(data, CommonConstants.SUCCESS, "SUCCESS", true);
	}

	public static <T> R<T> ok(T data, String message) {
		return restResult(data, CommonConstants.SUCCESS, message, true);
	}

	public static <T> R<T> fail() {
		return restResult(null, CommonConstants.FAIL, null, false);
	}

	public static <T> R<T> fail(String message) {
		return restResult(null, CommonConstants.FAIL, message, false);
	}

	public static <T> R<T> fail(T data) {
		return restResult(data, CommonConstants.FAIL, null, false);
	}

	public static <T> R<T> fail(T data, String message) {
		return restResult(data, CommonConstants.FAIL, message, false);
	}

	public static <T> R<T> fail(int code, String message){
		return restResult(null, code, message, false);
	}

	public static <T> R<T> fail(int code, T data, String message){
		return restResult(data, code, message, false);
	}

	private static <T> R<T> restResult(T data, int code, String message, boolean success) {
		R<T> apiResult = new R<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMessage(message);
		apiResult.setSuccess(success);
		return apiResult;
	}
}

