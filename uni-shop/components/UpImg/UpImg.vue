<template>
	<view>
		<view class="up_img_wrap">
			<image :src="src"></image>
			<icon type="clear" size="23" color="red">
			</icon>
		</view>
	</view>
</template>

<script>
	export default {
		name: "UpImg",
		props: {
			src:{
				type:String,
				value:""
			}
		},
		data() {
			return {

			};
		}
	}
</script>

<style>
.up_img_wrap{
  width: 90rpx;
  height: 90rpx;
  position: relative;
}
.up_img_wrap image{
  width: 100%;
  height: 100%;
  border-radius: 15rpx;
}
.up_img_wrap icon{
  position: absolute;
  top:-22rpx;
  right: -22rpx;
}
</style>
