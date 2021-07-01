<template>
	<view>
		<mySearch></mySearch>
		<myTab :tabs="tabs" @tabsItemChange="tabsItemChange">
			<block v-if="tabs[0].isActive">
			    <view class="first_tab">
			        <navigator class="goods_item"
			        v-for="(item,index) in goodsList"
			        :key="item.goods_id"
			        :url="'/pages/goods_detail/goods_detail?goods_id='+item.goods_id"
			        >
			            <!-- 左侧 图片容器 -->
			            <view class="goods_img_wrap">
			              <image mode="widthFix" :src="item.goods_small_logo?item.goods_small_logo:'https://ww1.sinaimg.cn/large/007rAy9hgy1g24by9t530j30i20i2glm.jpg'"></image>
			            </view>
			            <!-- 右侧 商品容器 -->
			            <view class="goods_info_wrap">
			              <view class="goods_name">{{item.goods_name}}</view>
			              <view class="goods_price">￥{{item.goods_price}}</view>
			            </view>
			          </navigator>
								<view class="top" :style="{'display':(flag===true? 'block':'none')}">
								  <uni-icons class="topc" type="arrowup" color="#ec7f11" size="30" @click="backtop"></uni-icons>
								</view>
			    </view>
			  </block>
			  <block v-if="tabs[1].isActive">1</block>
			  <block v-if="tabs[2].isActive">2</block>
		</myTab>
	</view>
</template>

<script>
	// 导入搜索框组件
	import mySearch from "@/components/mySearch/mySearch.vue"
	import myTab from "@/components/myTab/myTab.vue"
	export default {
		components:{
			mySearch,
			myTab
		},
		data() {
			return {
				goods_id:"",
				tabs:[
					{
						id: 0,
						value: "综合",
						isActive: true
					},
					{
						id: 1,
						value: "销量",
						isActive: false
					},
					{
						id: 2,
						value: "价格",
						isActive: false
					}
				],
				goodsList:[],
				// 接口要的参数
				QueryParams:{
					query:"",
					cid:"",
					pagenum:1,
					pagesize:10
				},
				// 总页数
				totalPages:1,
				flag:false
			}
		},
		methods: { 
			tabsItemChange(e){
				this.tabs.forEach((v,i) => v.isActive=v.id===e.id)
			},
			// 获取商品列表数据
			async getGoodsList(){
				console.log('传递参数:',this.QueryParams);
				const res=await this.$api({url:"/goods/search",data:this.QueryParams});
				// 获取 总条数
				const total=res.data.message.total;
				// 计算总页数
				this.totalPages=Math.ceil(total/this.QueryParams.pagesize);
				console.log(this.totalPages,res);
				// 拼接了数组
				this.goodsList=[...this.goodsList,...res.data.message.goods];
				console.log('获取到商品列表',this.goodsList);
				// 关闭下拉刷新的窗口 如果没有调用下拉刷新的窗口 直接关闭也不会报错  
				uni.stopPullDownRefresh();
			},
			// 页面上滑 滚动条触底事件
			onReachBottom(){
			//  1 判断还有没有下一页数据
				if(this.QueryParams.pagenum>=this.totalPages){
					// 没有下一页数据
					console.log('%c'+"没有下一页数据","color:white;font-size:48px;border-radius:10px;pading:5px 10px;background-image:linear-gradient(to right,#73ffd8,pink)");
					uni.showToast({ title: '没有下一页数据' });
						
				}else{
					// 还有下一页数据
					console.log('%c'+"有下一页数据","color:white;font-size:48px;border-radius:10px;pading:5px 10px;background-image:linear-gradient(to right,#73ffd8,pink)");
					this.QueryParams.pagenum++;
					this.getGoodsList();
				}
			},
			// 下拉刷新事件 
			onPullDownRefresh(){
				// 1 重置数组
				this.goodsList=[];
				// 2 重置页码
				this.QueryParams.pagenum=1;
				// 3 发送请求
				this.getGoodsList();
			},
		 // 点击返回顶部按钮 跳回顶部
			backtop() {
				console.log('返回顶部');
				uni.pageScrollTo({
					scrollTop: 0,
					duration: 300
				});
			},
		},
		onLoad(options){
			console.log("options",options);
			this.QueryParams.cid=options.cid||"";
			this.QueryParams.query=options.query||"";
			this.getGoodsList();
		},
		onPageScroll(e) {
			if (e.scrollTop >= 100) {
				this.flag = true;
			} else {
				this.flag = false;
			}
		},
	}
</script>

<style>
@import url("./goods_list.css");
.top {
	position: relative;
	display: none;
}
.topc {
	position: fixed;
	right: 0;
	background: rgba(255, 255, 255, 0.5);
	top: 92%;
	right: 5%;
	border-radius: 50%;
	border: 1rpx solid silver;
}
</style>
