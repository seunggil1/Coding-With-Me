<template>
	<q-page class="flex flex-center">
		<AtomBasic1Button></AtomBasic1Button>
		<AtomBasic2Button></AtomBasic2Button>
		<AtomSearchIconButton></AtomSearchIconButton>
		<AtomScoreIconButton></AtomScoreIconButton>
		<AtomMyPageButton></AtomMyPageButton>
		<AtomPlusButton></AtomPlusButton>
		<AtomEditIcon></AtomEditIcon>
		<AtomDeleteIcon></AtomDeleteIcon>
		<AtomLogoutButton></AtomLogoutButton>
		<AtomRadio></AtomRadio>
		<AtomDropdown></AtomDropdown>
		<AtomCalendar></AtomCalendar>
	</q-page>
</template>

<script>
import { defineComponent } from 'vue';
import AtomBasic1Button from '../components/atoms/AtomBasic1Button.vue';
import AtomBasic2Button from '../components/atoms/AtomBasic2Button.vue';
import AtomSearchIconButton from '../components/atoms/AtomSearchIconButton.vue';
import AtomScoreIconButton from 'src/components/atoms/AtomScoreIconButton.vue';
import AtomMyPageButton from '../components/atoms/AtomMyPageButton.vue';
import AtomPlusButton from '../components/atoms/AtomPlusButton.vue';
import AtomEditIcon from '../components/atoms/AtomEditIcon.vue';
import AtomLogoutButton from '../components/atoms/AtomLogoutButton.vue';
import AtomRadio from '../components/atoms/AtomRadio.vue';
import AtomDeleteIcon from 'src/components/atoms/AtomDeleteIcon.vue';
import AtomDropdown from 'src/components/atoms/AtomDropdown.vue';
import AtomCalendar from '../components/atoms/AtomCalendar.vue';

export default defineComponent({
	name: 'IndexPage',
	components: {
		AtomBasic1Button,
		AtomBasic2Button,
		AtomSearchIconButton,
		AtomScoreIconButton,
		AtomMyPageButton,
		AtomPlusButton,
		AtomEditIcon,
		AtomLogoutButton,
		AtomRadio,
		AtomDeleteIcon,
		AtomDropdown,
		AtomCalendar,
	},
});
</script>
