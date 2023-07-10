<!-- FilterComponent.vue -->
<template>
	<div v-if="visible" class="filter-menu">
	<select v-model="filter.participantType" name="availableTransferService">
		<option value="" disabled selected hidden>Тип участника</option>
						<option v-for="(type, index) in participantTypes" :value="type.code" :key="index">
							{{ type }}
						</option>
					</select>

		<input v-model="filter.validFrom" type="date" />
		<input v-model="filter.validUntil" type="date" />

		<div class="buttons">
			<button style="background-color: #5A77DF; color: white;border: none;" @click="applyFilter">Применить</button>
			<button @click="resetFilter">Отменить</button>
		</div>

	</div>
</template>

<script>
export default {
	props: ['visible', 'participantTypes', 'shouldResetFilter'],
	data() {
		return {
			filter: {
				participantType: '',
				validFrom: '',
				validUntil: ''
			}
		}
	},
	methods: {
		applyFilter() {
			this.$emit('apply-filter', this.filter);
		},
		resetFilter() {
			this.filter = {
				participantType: '',
				validFrom: '',
				validUntil: ''
			};
			this.$emit('reset-filter');
		}
	},
	watch: {
        shouldResetFilter: function() {
            this.resetFilter();
        }
    }
}
</script>
<style scoped>
@import '@/assets/css/filter.css';
</style>
