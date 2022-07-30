<template>
	<div class="q-pa-md" style="font-family: 'Elice Digital Baeum'">
		<div class="q-gutter-md">
			<div class="class-info">
				<div class="col q-ma-lg">
					<!-- 반, 강의제목, 날짜 -->
					<div class="info" style="margin-bottom: 20px">
						<p style="font-size: 40px">개나리반의 강의</p>
						<p style="font-size: 18px">제목: 프로젝트 1</p>
						<p style="font-size: 18px">날짜: 2022.07.25</p>
					</div>
				</div>
				<div class="col">
					<AtomBasic1Button
						class="button"
						push
						label="강의 입장"
						style="font-family: 'Elice Digital Baeum'"
					></AtomBasic1Button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import AtomBasic1Button from 'src/components/atoms/AtomBasic1Button.vue';
export default {
	name: 'ClassInfo',
	setup() {
		return {};
	},
	components: { AtomBasic1Button },
};
</script>

<style lang="scss" scoped>
@font-face {
	font-family: 'GmarketSansLight';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
.class-info {
	height: 300px;
	width: 700px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 3px 3px 3px 0px rgba(0, 0, 0, 0.2);
}

.button {
	margin-top: 60px;
	margin-left: 20px;
}

// .info {
// 	margin-top: 150px;
// }
</style>
