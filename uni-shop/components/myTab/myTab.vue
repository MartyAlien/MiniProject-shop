<template>
	<view class="tabs">
	  <view class="tabs_title">
	    <view
	    v-for="(item,index) in tabs"
	    :key="item.id"
	    :class=" {title_item:true,active:item.isActive} "
	    @click="handleItemTap(item.id)"
	    >
	    {{item.value}}
	  </view>
	  </view>
	  <view class="tabs_content">
	    <slot></slot>
	  </view>
	</view>
</template>

<script>
	export default{
		props:{
			tabs:Array
		},
		data(){
			return{
				
			}
		},
		methods:{
			handleItemTap(id){
				this.$emit("tabsItemChange",{id});
			}
		}
	}
</script>

<style>
	.tabs{}
	.tabs_title{
	  display: flex;
	 
	}
	.title_item{
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  flex: 1;
	  padding: 15rpx 0;
	}
	.active{
	  color:#f00;
	  border-bottom: 5rpx solid currentColor;
	}
	.tabs_content{}
</style>
