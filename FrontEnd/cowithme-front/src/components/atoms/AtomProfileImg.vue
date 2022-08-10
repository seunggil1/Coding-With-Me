<template>
	<q-img :src="profile" />
</template>

<script>
import profile from 'src/assets/picture/profile.jpg';
export default {
	name: 'AtomProfileImg',
	setup() {
		return { profile };
	},
};
</script>

<style lang="scss" scoped></style>
