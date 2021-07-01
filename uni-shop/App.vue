<script>
	export default {
		onLaunch: function() {
			// console.warn('当前组件仅支持 uni_modules 目录结构 ，请升级 HBuilderX 到 3.1.0 版本以上！')
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	@import url("./static/css/iconfont.css");
	/*每个页面公共css */
	page,view,text,swiper,swiper-item,image,navigator{
		padding: 0;
		margin: 0;
		box-sizing: border-box;
	}
</style>
