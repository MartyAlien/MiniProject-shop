<template>
	<view>
		<myTab :tabs="tabs" @tabsItemChange="handleTabsItemChange">
			<view class="fb_main">
				<view class="fb_title">问题的种类</view>
				<view class="fb_tips">
					<text>功能建议</text>
					<text>购买遇到问题</text>
					<text>性能问题</text>
					<text>其他</text>
				</view>
				<view class="fb_content">
					<textarea :value="textVal" @input="handleTextInput" placeholder="请描述一下您的问题"> </textarea>
					<view class="fb_tool">
						<button @click="handleChooseImg">+</button>

						<view class="up_img_item" v-for="(src,index) in chooseImgs" :key="src"
							@click="handleRemoveImg(index)">
							<UpImg :src="src"></UpImg>
						</view>

					</view>
				</view>
				<view class="form_btn_wrap">
					<button @click="handleFormSubmit" type="warn">
						<icon type="success_no_circle" size="23" color="white">

						</icon>

						提交
					</button>

				</view>
			</view>
		</myTab>
	</view>
</template>

<script>
	import myTab from "@/components/myTab/myTab.vue"
	import UpImg from "@/components/UpImg/UpImg.vue"
	export default {
		components: {
			myTab,
			UpImg
		},
		data() {
			return {
				tabs: [{
						id: 0,
						value: "体验问题",
						isActive: true
					},
					{
						id: 1,
						value: "商品、商家投诉",
						isActive: false
					}
				],
				// 被选中的图片路径 数组
				chooseImgs: [],
				// 文本域的内容
				textVal: "",
				// 外网的图片的路径数组
				UpLoadImgs: [],
			}
		},
		methods: {
			handleTabsItemChange(e) {
				this.tabs.forEach((v, i) => v.isActive = v.id === e.id)
			},
			// 点击 “+” 选择图片
			handleChooseImg() {
				// 2 调用小程序内置的选择图片api
				uni.chooseImage({
					// 同时选中的图片的数量
					count: 9,
					// 图片的格式  原图  压缩
					sizeType: ['original', 'compressed'],
					// 图片的来源  相册  照相机
					sourceType: ['album', 'camera'],
					success: (result) => {
						// 图片数组 进行拼接 
						console.log("result", result);
						this.chooseImgs = [...this.chooseImgs, ...result.tempFilePaths]
						console.log("this.chooseImgs", this.chooseImgs);
					}
				});
			},
			// 点击 自定义图片组件
			handleRemoveImg(index) {
				// 3 获取data中的图片数组
				// 4 删除元素
				this.chooseImgs.splice(index, 1);
			},
			// 文本域的输入的事件
			handleTextInput(e) {
				this.textVal = e.detail.value;
			},
			async urlTobase64(url) {
				const base64 = await this.$pathToBase64(url);
				const str = (base64 + '').substr((base64 + '').indexOf(',') + 1);
				return str;
			},
			// 提交按钮的点击
			async handleFormSubmit() {
				// 1 获取文本域的内容 图片数组
				const {textVal,chooseImgs} = this.$data;
				// 2 合法性的验证
				if (!textVal.trim()) {
					// 不合法
					uni.showToast({
						title: '输入不合法',
						icon: 'none',
						mask: true
					});
					return;
				}
				// 3 准备上传图片 到专门的图片服务器 
				// 上传文件的 api 不支持 多个文件同时上传  遍历数组 挨个上传 
				// 显示正在等待的图片
				uni.showLoading({
					title: "正在上传中",
					mask: true
				});
				// 判断有没有需要上传的图片数组
				let that=this;
				if (chooseImgs.length != 0) {
					chooseImgs.forEach(async (v, i) => {
						const sendBody=await that.getSendBody(v,i);
						console.log(i,sendBody);
						const r=await uni.request({
							// 图片要上传到哪里
							url: 'https://gitee.com/api/v5/repos/'+sendBody.owner+'/'+sendBody.repo+'/contents/'+sendBody.path,
							data:sendBody,
							method:"POST"
						});
						console.log(i,r);
						if(r){
							await this.UpLoadImgs.push(r[1].data.content.download_url);
						}
						if (i === chooseImgs.length - 1) {
							uni.hideLoading();
							await uni.setStorageSync("UpLoadImgs",this.UpLoadImgs);
							await uni.showToast({
								title:"上传成功"
							})
							this.textVal="";
							this.chooseImgs=[];
							uni.navigateBack({
								delta: 1
							});
						}
					})
				} else {
					uni.hideLoading();
					console.log("只是提交了文本");
					uni.navigateBack({
						delta: 1
					});

				}
			},
			async getSendBody(v,i){
				const sendBody={access_token:'f94bf71e5763c64909c16401025c5a10',
					owner:"tanyajun",
					repo:"picgo-for-myself",
					path:"",
					content:"",
					message:""};
				const fileName=(new Date()).getTime()+'_'+i+".jpg";
				sendBody.path='imgs/'+fileName;
				sendBody.content=await this.urlTobase64(v);
				sendBody.message="uniapp上传的文件:"+fileName;
				return sendBody;
			}
		}
	}
</script>

<style>
	@import url("./feedback.css");
</style>
