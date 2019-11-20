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

package com.xuegao.educloud.common.constants;

public interface CommonConstants {
	/**
	 * 删除
	 */
	Byte STATUS_DEL = 1;
	/**
	 * 正常
	 */
	Byte STATUS_NORMAL = 0;

	/**
	 * 成功标记
	 */
	int SUCCESS = 200;

	/**
	 * 失败标记
	 */
	int FAIL = 500;

	/**
	 * 根节点
	 */
	int TREE_ROOT = 0 ;

	/**
	 * 默认排序
	 */
	int DEFAULT_SORT = 99;

	/**
	 * 第一页
	 */
	String FIRST_PAGE = "1";

	/**
	 * 每页显示记录数
	 */
	String DEFAULT_PAGE_SIZE = "10";

}
