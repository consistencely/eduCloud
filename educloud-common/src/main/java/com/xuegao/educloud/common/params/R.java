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

package com.xuegao.educloud.common.params;

import com.xuegao.educloud.common.constants.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 响应信息主体
 *
 * @param <T>
 * @author lengleng
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
	private String msg;

	@Getter
	@Setter
	private boolean success;


	@Getter
	@Setter
	private T data;

	public static <T> R<T> ok() {
		return restResult(null, CommonConstants.SUCCESS, null, true);
	}

	public static <T> R<T> ok(T data) {
		return restResult(data, CommonConstants.SUCCESS, null, true);
	}

	public static <T> R<T> ok(T data, String msg) {
		return restResult(data, CommonConstants.SUCCESS, msg, true);
	}

	public static <T> R<T> fail() {
		return restResult(null, CommonConstants.FAIL, null, false);
	}

	public static <T> R<T> fail(String msg) {
		return restResult(null, CommonConstants.FAIL, msg, false);
	}

	public static <T> R<T> fail(T data) {
		return restResult(data, CommonConstants.FAIL, null, false);
	}

	public static <T> R<T> fail(T data, String msg) {
		return restResult(data, CommonConstants.FAIL, msg, false);
	}

	public static <T> R<T> fail(int code, String msg){
		return restResult(null, code, msg, false);
	}

	public static <T> R<T> fail(int code, T data, String msg){
		return restResult(data, code, msg, false);
	}

	private static <T> R<T> restResult(T data, int code, String msg, boolean success) {
		R<T> apiResult = new R<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		apiResult.setSuccess(success);
		return apiResult;
	}
}

