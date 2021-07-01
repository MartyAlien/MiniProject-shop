// 封装get请求
// 同时发送异步代码的次数
let ajaxTimes=0;
const baseUrl = "http://api-hmugo-web.itheima.net/api/public/v1"
// const baseUrl = "http://localhost/api/v1"
export const api = (params)=>{
	 // 判断 url中是否带有 /my/ 请求的是私有的路径 带上header token
	 console.log("params",params);
	  let header={...params.header};
	  console.log("header",header);
	  if(params.url.includes("/my/")){
	    // 拼接header 带上token
	    header["Authorization"]=uni.getStorageSync("token");
	  }
		console.log("header==",header);
	
	ajaxTimes++;
	// 显示加载中 效果
	uni.showLoading({
		title: "加载中",
		mask: true
	});
	return new Promise((resolve,reject)=>{
		uni.request({
			...params,
		  header:header,
			url: baseUrl+params.url,
			success(res) {
				/* if(res.data.meta.status !== 200) {
					return uni.showToast({
						title: '获取数据失败'
					})
				} */
				resolve(res)
			},
			fail(err) {
				uni.showToast({
					title: '获取数据失败'
				})
				reject(err)
			},
		  complete:()=>{
				ajaxTimes--;
				if(ajaxTimes===0){
					//  关闭正在等待的图标
					uni.hideLoading();
				}
		  }
		})
	})
}