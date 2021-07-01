<template>
	<view>
		<button type="primary" plain @click="handleGetUserInfo">
			获取授权
		</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {
			async handleGetUserInfo() {
				try {
					const userInfo = await uni.getUserProfile({
						desc: '用于完善会员资料'
					});
					console.log(userInfo[1]);
					// 1 获取用户信息
					const {
						encryptedData,
						rawData,
						iv,
						signature
					} = userInfo[1];
					// 2 获取小程序登录成功后的code
					const loginRes = await uni.login({
						timeout: 10000
					});
					console.log('loginRes', loginRes);
					const {
						code
					} = loginRes[1];
					console.log(code);
					const loginParams = {
						encryptedData,
						rawData,
						iv,
						signature,
						code,
						appid: 'wxe4d6140c671e0230',
						secret: '966bf195d20193b0bbb1c509cfdfaa8c'
					};
					console.log("loginParams", loginParams);
					//  3 发送请求 获取用户的token
					const res = await uni.request({
						url: "http://www.fulfill.com.cn/api/v1/user/wxlogin",
						data: loginParams,
						method: "post",
						header: {
							'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
						},
					});
					let {
						token
					} = res[1].data.message;
					await uni.showToast({
							 title: '获取token'+(token?"成功":"失败"),
							 duration: 2000
					 });
					uni.setStorageSync("token", token);
					uni.navigateBack({
						delta: 1
					});

				} catch (error) {
					console.log(error);
				}
			}
		}
	}
</script>

<style>
	button {
		margin-top: 40rpx;
		width: 70%;
	}
</style>
