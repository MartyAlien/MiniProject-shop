<template>
	<view>
		<view class="navbar-sea">
			<!-- <input type="text" value="" placeholder="请输入搜索内容" /> -->
			<uni-search-bar placeholder="请输入搜索内容" 
			:radius="100" 
			@confirm="search" 
			@input="input"
			@blur="blur"
			@cancel="cancel"
			@clear="clear"
			clearButton="auto"
			cancelButton="none"
			maxlength=20></uni-search-bar>
		</view>
		<view class="search_content">
		  <navigator :url="'/pages/goods_detail/goods_detail?goods_id='+item.goods_id"
			 class="search_item"
			 v-for="item in goods"
			 :key="item.goods_id">
		    {{item.goods_name}}
		  </navigator>
		</view> 
	</view>
</template>

<script>
	export default {
		data() {
			return {
				goods:[],
				timeId:-1
			}
		},
		methods: {
			search(e) {
				if(!this.isRight(e.value)){
					console.log("commit:输入内容不合法");
					return;
				}
				clearTimeout(this.timeId);
				let that=this;
				this.timeId=setTimeout(()=>{
					that.qsearch(e.value)
				},0);
			},
			input(e) {
				if(!this.isRight(e)){
					console.log("input:输入内容不合法");
					return;
				}
				clearTimeout(this.timeId);
				let that=this;
				this.timeId=setTimeout(()=>{
					that.qsearch(e)
				},500);
			},
			blur(e) {
				if(!this.isRight(e.value)){
					console.log("blur:输入内容不合法");
					return;
				}
				clearTimeout(this.timeId);
				let that=this;
				this.timeId=setTimeout(()=>{
					that.qsearch(e.value)
				},10);
			},
			focus(e) {
				console.log("获得焦点监听",e);
			},
			cancel(e) {
				console.log("取消监听",e);
			},
			clear(e) {
				console.log("清除监听",e);
			},
			async qsearch(value){
				const res=await this.$api({url:"/goods/qsearch",data:{query:value}});
				this.goods=res.data.message;
			},
			isRight(e){
				if(!e.trim()){
					// 如果输入框的值不合法
					this.goods=[];
					clearTimeout(this.timeId);
					return false;
				}
				return true;
			}
		}
	}
</script>

<style>
	@import url("./search.css");
	.navbar-sea {
		background-image:linear-gradient(to right,#0c5d82,#f8614d);
		padding: 8rpx;
	}

	.navbar-sea input {
		border: 1px silver solid;
		border-radius: 20rpx;
		padding: 10rpx 20rpx;
		background-color: #fff;
	}
</style>
