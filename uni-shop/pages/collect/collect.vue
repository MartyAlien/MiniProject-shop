<template>
	<view>
		<myTab :tabs="tabs" @tabsItemChange="handleItemTap">

			<view class="collect_main">
				<view class="collect_title">
					<text class="collect_tips active">全部</text>
					<text class="collect_tips">正在热卖</text>
					<text class="collect_tips">即将上线</text>
				</view>
				<view class="collect_content">
					<navigator class="goods_item" v-for="item in collect" :key="item.goods_id"
						:url="'/pages/goods_detail/goods_detail?goods_id='+item.goods_id">
						<!-- 左侧 图片容器 -->
						<view class="goods_img_wrap">
							<image mode="widthFix"
								:src="item.goods_small_logo?item.goods_small_logo:'https://ww1.sinaimg.cn/large/007rAy9hgy1g24by9t530j30i20i2glm.jpg'">
							</image>
						</view>
						<!-- 右侧 商品容器 -->
						<view class="goods_info_wrap">
							<view class="goods_name">{{item.goods_name}}</view>
							<view class="goods_price">￥{{item.goods_price}}</view>
						</view>
					</navigator>
				</view>
			</view>

		</myTab>
	</view>
</template>

<script>
	import myTab from "@/components/myTab/myTab.vue"
	export default {
		components: {
			myTab
		},
		data() {
			return {
				tabs: [{
						id: 0,
						value: "商品收藏",
						isActive: true
					},
					{
						id: 1,
						value: "品牌收藏",
						isActive: false
					},
					{
						id: 2,
						value: "店铺收藏",
						isActive: false
					},
					{
						id: 3,
						value: "浏览器足迹",
						isActive: false
					}
				],
				collect: []
			}
		},
		methods: {
			handleItemTap(e) {
				// 1 获取被点击的标题索引
				this.tabs.forEach((v,i) => v.isActive=v.id===e.id)
			}
		},
		onShow() {
			this.collect = wx.getStorageSync("collect") || [];

		},
	}
</script>

<style>
	@import url("./collect.css");
</style>
