<script>
  export let key = 'Key'
  export let value = 'Value'
</script>

<div class="row">
    <div class="key">{key}</div>
    <div class="value">{value}</div>
</div>

<style>
    .row {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        padding: 8px;
    }

    .row div {
        flex: 1 1 0px;
    }

    .row .key {
        font-weight: bold;
        text-align: right;
        padding-right: 24px;
    }

    .row .value {
        text-align: left;
        padding-left: 24px;
    }

    .row:nth-child(odd) {
        background-color: antiquewhite;
    }
</style>
