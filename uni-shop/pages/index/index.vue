<template>
	<view>
		<mySearch></mySearch>

		<!-- 轮播图 开始 -->

		<uni-swiper-dot :info="swiper" :current="current" field="每日上新" :mode="mode" :dotsStyles="dots">
			<swiper @change="change" autoplay="true" circular="true">
				<swiper-item v-for="(item ,index) in swiper" :key="index">
					<navigator :url="item.navigator_url">
						<image mode="widthFix" :src="item.image_src" style="width: 100%;"></image>
					</navigator>
				</swiper-item>
			</swiper>
		</uni-swiper-dot>
		<!-- 轮播图 结束 -->
		<!-- 导航 开始 -->
		<view class="index_cate">
			<navigator v-for="(item,index) in catiList" :key="index" url="/pages/cartgroy/cartgroy"
				open-type="switchTab">
				<image :src="item.image_src" mode="widthFix"></image>
			</navigator>
		</view>
		<!-- 导航 结束 -->
		<!-- 楼层 开始 -->
		<view class="index_floor">
			<view class="floor_group" v-for="item in floorList" :key="item.floor_title.name">
				<!-- 标题 -->
				<view class="floor_title">
					<image :src="item.floor_title.image_src" mode="widthFix"></image>
				</view>
				<!-- 内容 -->
				<view class="floor_list">
					<navigator v-for="(product_item,index2) in item.product_list" 
					:key="product_item.name"
					:url="product_item.navigator_url">
						<image :src="product_item.image_src" :mode="index2===0?'widthFix':'scaleToFill'"></image>
					</navigator>
				</view>
			</view>

		</view>
		<!-- 楼层 结束 -->
	</view>
</template>

<script>
	// 导入搜索框组件
	import mySearch from "@/components/mySearch/mySearch.vue"
	export default {
		data() {
			return {
				current: 0,
				mode: 'default',
				dots: {
					width: 6,
					selectedBackgroundColor: "#3f7788"
				},
				swiper: [],
				catiList: [],
				floorList:[]
			}
		},
		methods: {
			change(e) {
				this.current = e.detail.current;
			},
			getSwiperList() {
				this.$api({
					url: "/home/swiperdata",
					method: "GET"
				}).then(resp => {
					this.swiper = resp.data.message;
				}).catch(e => {
					console.log(e);
				})
			},
			getCatiList() {
				this.$api({
					url: "/home/catitems",
					method: "GET"
				}).then(resp => {
					this.catiList = resp.data.message;
				}).catch(e => {
					console.log(e);
				})
			},
			getFloorList() {
				this.$api({
					url: "/home/floordata",
					method: "GET"
				}).then(resp => {
					this.floorList = resp.data.message;
				}).catch(e => {
					console.log(e);
				})
			}
		},
		onLoad() {
			this.getSwiperList(); // 加载轮播图
			this.getCatiList(); // 加载导航数据
			this.getFloorList() // 加载楼层数据
		}
	}
</script>

<style>
	@import url("@/pages/index/index.css");
</style>
