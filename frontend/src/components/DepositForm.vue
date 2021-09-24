<template>
      <div id="deposit-form" class="grid-cols-2 grid-gap-5 max-w-lg mx-auto">
        <h1 class="text-3xl lg:text-4xl font-semibold text-yellow-100">How much would you like to deposit?</h1>

        <div class="grid gap-3 grid-cols-2 md:grid-cols-5">

        <kings-input type="number" label="$5" placeholder="$" v-model.number="this.depositObj.num5"/>

        <kings-input type="number" label="$10" placeholder="$" v-model.number="this.depositObj.num10"/>

        <kings-input type="number" label="$20" placeholder="$" v-model.number="this.depositObj.num20"/>

        <kings-input type="number" label="$50" placeholder="$" v-model.number="this.depositObj.num50"/>

        <kings-input type="number" label="$100" placeholder="$" v-model.number="this.depositObj.num100"/>

        </div>
        <div class="flex mt-4">
          <kings-button @click="handleCancel" class="flex-1 mr-4">Cancel</kings-button>
          <kings-button button-type="primary" @click="deposit" class="flex-1"> Deposit {{totalAmount}} </kings-button>
        </div>

      </div>

  <kings-error v-model:form-error="formError"/>

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
      depositObj: {
        num5c: 0,
        num10c: 0,
        num20c: 0,
        num50c: 0,
        num1: 0,
        num2: 0,
        num5: 0,
        num10: 0,
        num20: 0,
        num50: 0,
        num100: 0
      },
      total: 0
    }
  },
  computed: {
      totalAmount() {
        this.total = this.depositObj.num5 * 5 + this.depositObj.num10 * 10 + this.depositObj.num20 * 20 + this.depositObj.num50 * 50 + this.depositObj.num100 * 100;
        return `$${this.total}`;
      }
  },
  methods: {
      async handleCancel() {
        await this.$router.back();
      },
      async deposit () {

        try {
            if (!(Number.isInteger(this.depositObj.num5) & Number.isInteger(this.depositObj.num10) & Number.isInteger(this.depositObj.num20) &
             Number.isInteger(this.depositObj.num50) & Number.isInteger(this.depositObj.num100))) {
                throw "Please enter only whole numbers";
             }

            if (this.depositObj.num5 == 0 & this.depositObj.num10 == 0 & this.depositObj.num20 == 0 & this.depositObj.num50 == 0 & this.depositObj.num100 == 0) {
                throw "You must deposit more than $0";
            }

            let response = await AXIOS.post('/api/transaction/deposit', this.depositObj);

            if (response.status === 200) {

                if (response.data.success) {
                    await this.$router.push({name: "Receipt", params: { id: response.data.result.id }})
                } else {
                    this.formError = response.data.error;
                }

            }
        } catch (e) {
            if (this.depositObj.num5 < 0 | this.depositObj.num10 < 0 | this.depositObj.num20 < 0 | this.depositObj.num50 < 0 | this.depositObj.num100 < 0) {
                this.formError = "Please enter an amount greater than 0 for each of the fields";
            }
            else {
                this.formError = e;
            }
        }

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