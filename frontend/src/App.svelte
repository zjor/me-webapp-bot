<script>
  import { onMount } from 'svelte'
  import Row from './lib/Row.svelte'

  let imageUrl = 'https://res.cloudinary.com/zjor-storage/image/upload/v1672684817/profile_small_zinhzf.png'

  let attributes = [
    {
      id: 'id',
      name: 'User ID',
      value: 12345
    },
    {
      id: 'username',
      name: 'Username',
      value: 'alice.brown'
    },
    {
      id: 'first_name',
      name: 'First name',
      value: 'Alice'
    },
    {
      id: 'last_name',
      name: 'Last name',
      value: 'Brown'
    }
  ]

  function loadProfileImage(userId) {
    const _img = new Image()
    _img.onload = function () {
      imageUrl = _img.src
    }
    _img.src = `https://me-webapp-bot.projects.royz.cc/api/profile/photo/${userId}`
  }

  function updateAttributesFromTelegram() {
    const webApp = window.Telegram.WebApp
    const mainButton = webApp.MainButton
    const userInfo = webApp.initDataUnsafe
    const { user } = userInfo

    if (!user) {
      console.log('User data is not available')
      return
    }

    attributes.forEach((attr) => {
      if (user[attr.id]) {
        attr.value = user[attr.id]
      }
    })
    // triggers update
    attributes = attributes

    mainButton.setText('Close')
    mainButton.onClick(() => {
      webApp.close()
    })
    mainButton.enable()
    mainButton.show()
  }

  onMount(() => {
    updateAttributesFromTelegram()

    const {value: userId} = attributes.filter(({id}) => id === 'id')[0]
    loadProfileImage(userId)
  })

</script>

<main>
    <div>
        <img src={imageUrl} alt="">
    </div>
    {#each attributes as {id, name, value} (id)}
        <Row key={name} value={value}></Row>
    {/each}
</main>

<style>
    img {
        max-width: 240px;
        border-radius: 16px;
    }
</style>
