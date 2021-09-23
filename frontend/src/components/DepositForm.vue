<template>
    <div class="flex-grow flex flex-col items-center mt-12">
      <div id="deposit-form" class="grid gap-5 grid-cols-1 max-w-lg mx-auto">

        <kings-input type="number" label="Number of 5 c Coints" placeholder="$" v-model.number="amount5c"/>

        <kings-input type="number" label="Number of 10 c Coins" placeholder="$" v-model.number="amount10c"/>

        <kings-input type="number" label="Number of 20 c Coins" placeholder="$" v-model.number="amount20c"/>

        <kings-input type="number" label="Number of 50 c Coins" placeholder="$" v-model.number="amount50c"/>

        <kings-input type="number" label="Number of $1 Coins" placeholder="$" v-model.number="amount1"/>

        <kings-input type="number" label="Number of $2 Coins" placeholder="$" v-model.number="amount2"/>

        <kings-input type="number" label="Number of $5 Notes" placeholder="$" v-model.number="amount5"/>

        <kings-input type="number" label="Number of $10 Notes" placeholder="$" v-model.number="amount10"/>

        <kings-input type="number" label="Number of $20 Notes" placeholder="$" v-model.number="amount20"/>

        <kings-input type="number" label="Number of $50 Notes" placeholder="$" v-model.number="amount50"/>

        <kings-input type="number" label="Number of $100 Notes" placeholder="$" v-model.number="amount100"/>

        ${{totalAmount}}

        <div class="buttons flex flex-col">
                <kings-button
                    class="my-4"
                    @click="createAccount" button-type="primary"> Create Account
                </kings-button>
                <kings-button @click="handleCancel"> Cancel</kings-button>
        </div>
      </div>
      <kings-error :form-error="formError"/>
    </div>
</template>


<script>
import KingsInput from "./Base/KingsInput.vue";
import KingsButton from "./Base/KingsButton.vue";
import KingsError from "./Base/KingsError.vue";

import AXIOS from "../axios.js";

export default {
  name: "DepositForm",
  components: {
      KingsError,
      KingsButton,
      KingsInput
    },
  data() {
    return {
      formError: '',
      amount5c: 0,
      amount10c: 0,
      amount20c: 0,
      amount50c: 0,
      amount1: 0,
      amount2: 0,
      amount5: 0,
      amount10: 0,
      amount20: 0,
      amount50: 0,
      amount100: 0,
      total: 0
    }
  },
  computed: {
      totalAmount() {
        this.total = (this.amount5c * 0.05 + this.amount10c * 0.1 + this.amount20c * 0.2 + this.amount50c * 0.5 + this.amount1 +
                              this.amount2 * 2 + this.amount5 * 5 + this.amount10 * 10 + this.amount20 * 20 + this.amount50 * 50 + this.amount100 * 100).toFixed(2)
        return `Total to be deposited: $${this.total}`
      }
  },
  methods: {
      async handleCancel() {
        await this.$router.back();
      },
      async deposit () {
        if (!this.firstName || !this.depositAmount || !this.pin) {
            this.formError = 'Please fill out all the fields';
            return;
        }

        try {
            const postObj = {
            numCoinsAndNotes: [{num5c: this.amount5c, num10c: this.amount10c, num20c: this.amount20c, num50c: this.amount50c, num1: this.amount1, num2: this.amount2,
                     num5: this.amount5, num10: this.amount10, num20: this.amount20, num50: this.amount50, num100: this.amount100}]
            }

            let response = await AXIOS.post('/api/transaction/deposit', postObj);

            if (response.status === 200) {

                if (response.data.success) {
                    await this.$router.push({name: "Receipt", params: { id: response.data.result.id }})
                } else {
                    this.formError = response.data.error;
                }

            }
        } catch (e) {
            this.formError = e;
        }



        let response = await this.$store.dispatch("loginUser", numNotes);

        if (response?.data.error) {
          this.formError = response.data.error;
        }
      },

      clearFormError: async function (delay) {
        await new Promise(r => setTimeout(r, delay));
        this.formError = '';
      }
    },
    async created() {
      await this.$store.dispatch('initialise');
    }
}
</script>

<style scoped>

@tailwind base;
@tailwind components;
@tailwind utilities;

</style>