<template>
	<view>
		<view class="navbar-sea">
			<navigator url="/pages/search/search" class="navbar-inpput" open-type="navigate">
				<uni-icons type="search" size="15"/> 搜 索
			</navigator>
		</view>
	</view>
</template>

<script>
	export default {
		name: "mySearch",
		data() {
			return {

			};
		}
	}
</script>

<style>
	.navbar-sea {
		/*90deg=to right   270deg=to left*/
		background-image:linear-gradient(170deg,#236787,pink);
		padding: 10rpx;
	}

	.navbar-inpput {
		line-height: 70rpx;
		text-align: center;
		background-color: #FFFFFF;
		color: #808080;
		border-radius: 10rpx;
	}
</style>
