<template>
	<q-img :src="studentProfile" />
</template>

<script>
import studentProfile from 'src/assets/picture/studentProfile.jpg';
export default {
	name: 'AtomStudentProfile',
	setup() {
		return { studentProfile };
	},
};
</script>

<style lang="scss" scoped></style>
