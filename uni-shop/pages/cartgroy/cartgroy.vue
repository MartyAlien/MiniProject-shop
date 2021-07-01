<template>
	<view class="cates">
		<mySearch></mySearch>
		<view class="cates_container">
			<!-- 左侧菜单 -->
			<scroll-view scroll-y class="left_menu">
				<view v-for="(item,index) in leftMenuList" :class="{'menu_item': true,'active':index===currentIndex}"
					:key="index" @click="handleItemTap(index)">
					{{item}}
				</view>
			</scroll-view>
			<!-- 右侧商品内容 -->
			<scroll-view :scroll-top="scrollTop" scroll-y class="right_content">
				<view class="goods_group" v-for="(item1,index1) in rightContent">
					<view class="goods_title">
						<text class="delimiter">/</text>
						<text class="title">{{item1.cat_name}}</text>
						<text class="delimiter">/</text>
					</view>
					<view class="goods_list">
						<navigator open-type="navigate" v-for="(item2,index2) in item1.children" :key="index2"
							:url="'/pages/goods_list/goods_list?cid='+item2.cat_id"
							>
							<image mode="widthFix" :src="item2.cat_icon"></image>
							<view class="goods_name">{{item2.cat_name}}</view>
						</navigator>
					</view>
				</view>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	// 导入搜索框组件
	import mySearch from "@/components/mySearch/mySearch.vue"
	export default {
		components: {
			mySearch
		},
		data() {
			return {
				// 左侧的菜单数据
				leftMenuList: [],
				// 右侧的商品数据
				rightContent: [],
				// 被点击的左侧的菜单
				currentIndex: 0,
				// 右侧内容的滚动条距离顶部的距离
				scrollTop: 0,
				// 接口的返回数据
				Cates: [],
			}
		},
		methods: {
			handleItemTap(index) {
				/* 
				   1 获取被点击的标题身上的索引
				   2 给data中的currentIndex赋值就可以了
				   3 根据不同的索引来渲染右侧的商品内容
				    */
				this.rightContent = this.Cates[index].children;
				this.currentIndex = index;
				this.scrollTop = 0;
			},
			async getCates() {
				const res = await this.$api({
					url: "/categories"
				});
				this.Cates = res.data.message;

				// 把接口的数据存入到本地存储中
				uni.setStorageSync("cates", {
					time: Date.now(),
					data: this.Cates
				});
				// 构造左侧的大菜单数据
				this.leftMenuList = this.Cates.map(v => v.cat_name);
				// 构造右侧的商品数据
				this.rightContent = this.Cates[0].children;
			}
		},
		onLoad() {
			//  1 获取本地存储中的数据  (小程序中也是存在本地存储 技术)
			const cates = uni.getStorageSync("cates");
			// 2 判断
			if (!cates) {
				// 不存在  发送请求获取数据
				console.log("请求后台分类数据...");
				this.getCates();
			} else {
				// 有旧的数据 定义过期时间  10s 改成 5分钟
				if (Date.now() - cates.time > 1000 * 60 * 5) {
					// 重新发送请求
					console.log("本地分类数据过期，重新请求分类数据...");
					this.getCates();
				} else {
					// 可以使用旧的数据
					console.log("使用本地缓存分类数据...");
					this.Cates = cates.data;
					this.leftMenuList = this.Cates.map(v => v.cat_name);
					this.rightContent = this.Cates[0].children;
				}
			}
		}
	}
</script>

<style>
	@import url("@/pages/cartgroy/cartgroy.css");
</style>
